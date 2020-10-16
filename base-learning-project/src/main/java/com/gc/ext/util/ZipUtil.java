package com.gc.ext.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @author gaochao
 * @create 2020-09-03 11:04
 */
public class ZipUtil {

  /** 默认编码，使用平台相关编码 UTF-8*/
  private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  /** ZIP自定义包名 */
  private static final String ZIP_FILE_NAME = "test";
  /** 默认缓存大小 */
  public static final int DEFAULT_BUFFER_SIZE = 1024;


  public static void main(String[] args) {
    ZipUtil.zip("E:/PDF");
  }

  /**
   * 打包到当前目录，使用默认编码UTF-8
   *
   * @param srcPath 源文件路径
   * @return 打包好的压缩文件
   * @throws RuntimeException IO异常
   */
  public static File zip(String srcPath) throws RuntimeException {
    //判断路径
    if (srcPath == null || srcPath.length()== 0){
      throw new NullPointerException("File path is blank!");
    }
    return zip(new File(srcPath), DEFAULT_CHARSET);
  }

  /**
   * 打包到当前目录
   *
   * @param srcFile 源文件或目录
   * @param charset 编码
   * @return 打包好的压缩文件
   * @throws RuntimeException IO异常
   */
  public static File zip(File srcFile, Charset charset) throws RuntimeException {
    //将要生成的zip文件
    File zipFile = FileUtil.file(srcFile.getParentFile(), ZIP_FILE_NAME + ".zip");
    zip(zipFile, charset, false, srcFile);
    return zipFile;
  }

  /**
   * 对文件或文件目录进行压缩
   *
   * @param zipFile 生成的Zip文件，包括文件名。注意：zipPath不能是srcPath路径下的子文件夹
   * @param charset 编码
   * @param withSrcDir 是否包含被打包目录
   * @param srcFiles 要压缩的源文件或目录。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
   * @return 压缩文件
   * @throws RuntimeException IO异常
   */
  public static File zip(File zipFile, Charset charset, boolean withSrcDir, File... srcFiles) throws RuntimeException {
    validateFiles(zipFile, srcFiles);

    try (ZipOutputStream out = getZipOutputStream(zipFile, charset)){
      String srcRootDir;
      for (File srcFile : srcFiles) {
        // 如果只是压缩一个文件，则需要截取该文件的父目录
        srcRootDir = srcFile.getCanonicalPath();
        if (srcFile.isFile() || withSrcDir) {
          srcRootDir = srcFile.getParent();
        }
        // 调用递归压缩方法进行目录或文件压缩
        zip(srcFile, srcRootDir, out);
        out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
    return zipFile;
  }

  /**
   * 判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
   *
   * @param zipFile 压缩后的产生的文件路径
   * @param srcFiles 被压缩的文件或目录
   */
  private static void validateFiles(File zipFile, File... srcFiles) throws RuntimeException {
    for (File srcFile : srcFiles) {
      if (false == srcFile.exists()) {
        throw new RuntimeException("File not exist!");
      }

      try {
        // 压缩文件不能位于被压缩的目录内
        if (srcFile.isDirectory() && zipFile.getParent().contains(srcFile.getCanonicalPath())) {
          throw new RuntimeException("[zipPath] must not be the child directory of [srcPath]!");
        }

        if (false == zipFile.exists()) {
          FileUtil.touch(zipFile);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * 获得 {@link ZipOutputStream}
   *
   * @param zipFile 压缩文件
   * @param charset 编码
   * @return {@link ZipOutputStream}
   */
  private static ZipOutputStream getZipOutputStream(File zipFile, Charset charset) {
    try {
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(FileUtil.touch(zipFile)));
      return new ZipOutputStream(out, charset);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 递归压缩文件夹
   *
   * @param out 压缩文件存储对象
   * @param srcRootDir 压缩文件夹根目录的子路径
   * @param file 当前递归压缩的文件或目录对象
   * @throws RuntimeException IO异常
   */
  private static void zip(File file, String srcRootDir, ZipOutputStream out) throws RuntimeException {
    if (file == null) {
      return;
    }

    final String subPath = FileUtil.subPath(srcRootDir, file); // 获取文件相对于压缩文件夹根目录的子路径
    if(file.isDirectory()){// 如果是目录，则压缩压缩目录中的文件或子目录
      final File[] files = file.listFiles();
      if(files.length>0 && subPath !=null && subPath.length()> 0) {
        //加入目录，只有空目录时才加入目录，非空时会在创建文件时自动添加父级目录
        addDir(subPath, out);
      }
      //压缩目录下的子文件或目录
      for (File childFile : files) {
        zip(childFile, srcRootDir, out);
      }
    } else {// 如果是文件或其它符号，则直接压缩该文件
      addFile(file, subPath, out);
    }
  }

  /**
   * 在压缩包中新建目录
   *
   * @param path 压缩的路径
   * @param out 压缩文件存储对象
   * @throws Exception IO异常
   */
  private static void addDir(String path, ZipOutputStream out) throws RuntimeException {
    //path = cn.hutool.core.util.StrUtil.addSuffixIfNot(path, cn.hutool.core.util.StrUtil.SLASH);
    try {
      out.putNextEntry(new ZipEntry(path));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException();
    } finally {
      closeEntry(out);
    }
  }

  /**
   * 添加文件到压缩包
   *
   * @param file 需要压缩的文件
   * @param path 在压缩文件中的路径
   * @param out 压缩文件存储对象
   * @throws RuntimeException IO异常
   * @since 4.0.5
   */
  private static void addFile(File file, String path, ZipOutputStream out) throws RuntimeException {
    BufferedInputStream in = null;
    try {
        in = new BufferedInputStream(new FileInputStream(file));
        addFile(in, path, out);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      try {
        if (in != null){
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 添加文件流到压缩包，不关闭输入流
   *
   * @param in 需要压缩的输入流
   * @param path 压缩的路径
   * @param out 压缩文件存储对象
   * @throws Exception IO异常
   */
  private static void addFile(InputStream in, String path, ZipOutputStream out) throws RuntimeException {
    if(null == in) {
      return;
    }
    try {
      out.putNextEntry(new ZipEntry(path));
      IOUtil.copy(in, out, DEFAULT_BUFFER_SIZE);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException();
    } finally {
      closeEntry(out);
    }
  }

  /**
   * 关闭当前Entry，继续下一个Entry
   *
   * @param out ZipOutputStream
   */
  private static void closeEntry(ZipOutputStream out) {
    try {
      out.closeEntry();
    } catch (IOException e) {
      //ignore
    }
  }



  //静态内部类,进行文件处理
  private static class FileUtil{
    /**
     * 创建File对象
     *
     * @param parent 父文件对象
     * @param path 文件路径
     * @return File
     */
    public static File file(File parent, String path) {
      if (path == null && path.length() <= 0) {
        throw new NullPointerException("File path is blank!");
      }
      return new File(parent, path);
    }


    public static File touch(File file){
      if (null == file) {
        return null;
      }
      if (false == file.exists()) {
        mkParentDirs(file);
        try {
          file.createNewFile();
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException();
        }
      }
      return file;
    }

    /**
     * 创建所给文件或目录的父目录
     *
     * @param file 文件或目录
     * @return 父目录
     */
    public static File mkParentDirs(File file) {
      final File parentFile = file.getParentFile();
      if (null != parentFile && false == parentFile.exists()) {
        parentFile.mkdirs();
      }
      return parentFile;
    }

    /**
     * 获取相对子路径
     * @param rootDir
     * @param file
     * @return
     */
    public static String subPath(String rootDir, File file) {
      try {
        String dirPath = rootDir;
        String filePath =file.getCanonicalPath();
        if (dirPath != null && dirPath.length()>0 &&
                filePath != null && dirPath.length()>0 ) {

          dirPath = StrUtil.removeSuffix(dirPath, "/");
          //filePath = normalize(filePath);

          final String result = StrUtil.removePrefixIgnoreCase(filePath, dirPath);
          return StrUtil.removePrefix(result, "/");
        }
        return filePath;


      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException();
      }
    }

  }

  //静态内部类,字符处理类
  private static class StrUtil{
    /**
     * 去掉指定后缀
     *
     * @param str 字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSuffix(CharSequence str, CharSequence suffix) {
      if (isEmpty(str) || isEmpty(suffix)) {
        return str(str);
      }

      final String str2 = str.toString();
      if (str2.endsWith(suffix.toString())) {
        return subPre(str2, str2.length() - suffix.length());// 截取前半段
      }
      return str2;
    }

    /**
     * 切割指定位置之前部分的字符串
     *
     * @param string 字符串
     * @param toIndex 切割到的位置（不包括）
     * @return 切割后的剩余的前半部分字符串
     */
    public static String subPre(CharSequence string, int toIndex) {
      return sub(string, 0, toIndex);
    }

    /**
     * 改进JDK subString<br>
     * index从0开始计算，最后一个字符为-1<br>
     * 如果from和to位置一样，返回 "" <br>
     * 如果from或to为负数，则按照length从后向前数位置，如果绝对值大于字符串长度，则from归到0，to归到length<br>
     * 如果经过修正的index中from大于to，则互换from和to example: <br>
     * abcdefgh 2 3 =》 c <br>
     * abcdefgh 2 -3 =》 cde <br>
     *
     * @param str String
     * @param fromIndex 开始的index（包括）
     * @param toIndex 结束的index（不包括）
     * @return 字串
     */
    public static String sub(CharSequence str, int fromIndex, int toIndex) {
      if (isEmpty(str)) {
        return str(str);
      }
      int len = str.length();

      if (fromIndex < 0) {
        fromIndex = len + fromIndex;
        if (fromIndex < 0) {
          fromIndex = 0;
        }
      } else if (fromIndex > len) {
        fromIndex = len;
      }

      if (toIndex < 0) {
        toIndex = len + toIndex;
        if (toIndex < 0) {
          toIndex = len;
        }
      } else if (toIndex > len) {
        toIndex = len;
      }

      if (toIndex < fromIndex) {
        int tmp = fromIndex;
        fromIndex = toIndex;
        toIndex = tmp;
      }

      if (fromIndex == toIndex) {
        return "";
      }

      return str.toString().substring(fromIndex, toIndex);
    }

    /**
     * 忽略大小写去掉指定前缀
     *
     * @param str 字符串
     * @param prefix 前缀
     * @return 切掉后的字符串，若前缀不是 prefix， 返回原字符串
     */
    public static String removePrefixIgnoreCase(CharSequence str, CharSequence prefix) {
      if (isEmpty(str) || isEmpty(prefix)) {
        return str(str);
      }

      final String str2 = str.toString();
      if (str2.toLowerCase().startsWith(prefix.toString().toLowerCase())) {
        return subSuf(str2, prefix.length());// 截取后半段
      }
      return str2;
    }

    public static boolean isEmpty(CharSequence str) {
      return str == null || str.length() == 0;
    }

    /**
     * {@link CharSequence} 转为字符串，null安全
     *
     * @param cs {@link CharSequence}
     * @return 字符串
     */
    public static String str(CharSequence cs) {
      return null == cs ? null : cs.toString();
    }

    /**
     * 切割指定位置之后部分的字符串
     *
     * @param string 字符串
     * @param fromIndex 切割开始的位置（包括）
     * @return 切割后后剩余的后半部分字符串
     */
    public static String subSuf(CharSequence string, int fromIndex) {
      if (isEmpty(string)) {
        return null;
      }
      return sub(string, fromIndex, string.length());
    }

    /**
     * 去掉指定前缀
     *
     * @param str 字符串
     * @param prefix 前缀
     * @return 切掉后的字符串，若前缀不是 preffix， 返回原字符串
     */
    public static String removePrefix(CharSequence str, CharSequence prefix) {
      if (isEmpty(str) || isEmpty(prefix)) {
        return str(str);
      }

      final String str2 = str.toString();
      if (str2.startsWith(prefix.toString())) {
        return subSuf(str2, prefix.length());// 截取后半段
      }
      return str2;
    }
  }

  private static class IOUtil{

    /**
     * 拷贝流
     *
     * @param in 输入流
     * @param out 输出流
     * @param bufferSize 缓存大小
     * @return 传输的byte数
     * @throws RuntimeException IO异常
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize) throws RuntimeException {
      if (in == null){
        throw new NullPointerException("InputStream is null !");
      }
      if (out == null){
        throw new NullPointerException("OutputStream is null !");
      }
      if (bufferSize <= 0) {
        bufferSize = DEFAULT_BUFFER_SIZE;
      }

      byte[] buffer = new byte[bufferSize];
      long size = 0;

      try {
        for (int readSize = -1; (readSize = in.read(buffer)) != -1;) {
          out.write(buffer, 0, readSize);
          size += readSize;
          out.flush();

        }
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException();
      }
      return size;
    }
  }

}



