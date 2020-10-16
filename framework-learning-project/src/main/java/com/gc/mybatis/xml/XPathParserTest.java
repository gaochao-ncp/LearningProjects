package com.gc.mybatis.xml;

import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaochao
 * @create 2020-09-15 9:41
 */
public class XPathParserTest {

  public static String defaultEnCoding = "UTF-8";
  public static boolean isDecodePath = true;

  private static ConcurrentHashMap<String,String> xmlMap = new ConcurrentHashMap<>();

  /**
   * 获取文件路径
   * @param fileClassesPath
   * @return
   */
  public static String getResourceFile(String fileClassesPath) {
    URL url = findClassLoader().getResource(fileClassesPath);
    return getUrlFileAndDecode(url);
  }

  public static String getUrlFileAndDecode(URL url) {
    if (url == null) {
      return null;
    }
    return decodePath(url.getFile());
  }
  public static String decodePath(String path) {
    if (!isDecodePath) {
      return path;
    }
    if (path == null) {
      return null;
    }
    try {
      return URLDecoder.decode(path, defaultEnCoding);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("UnsupportedEncoding:" + defaultEnCoding, e);
    }
  }

  public static ClassLoader findClassLoader() {
    //类加载器，动态加载时使用，比使用Class.getClassLoader()安全。
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    if (loader == null) {
      loader = ClassLoader.getSystemClassLoader();
    }
    return loader;
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(get("INDUSTRY_CODE", "010208", "A", "out"));
  }

  private static String get(String name,String system,String key,String dire) throws FileNotFoundException{
    //String fileUrl = System.getProperty("user.dir")+"/test-mybatis-demo/src/main/resources/CodeConvert.xml";
    FileInputStream inputStream = new FileInputStream(getResourceFile("CodeConvert.xml"));
    XPathParser p = new XPathParser(inputStream);
    List<XNode> enums = p.evalNodes("/CodeConvert/enums[@name='"+name+"']/system[@name='"+system+"']/enum");
    enums.stream().forEach(e -> {
      String src = e.getStringAttribute("src");
      String dest = e.getStringAttribute("dest");
      xmlMap.put(src,dest);

    });
    String result = null;
    //传入in 由src转向dest
    if ("in".equals(dire)){
      result = xmlMap.get(key);
    }else if ("out".equals(dire)){
      //传入out 由dest转向src
      for (String k : xmlMap.keySet()) {
        if (xmlMap.get(k).equals(key)) {
          return k;
        }
      }
    }
    return result;
  }
}
