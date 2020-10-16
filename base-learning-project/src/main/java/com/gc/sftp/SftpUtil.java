package com.gc.sftp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于jsch的sftp实现
 * @author gaochao
 * @create 2020-09-16 10:28
 */
public class SftpUtil {

  private static final Log log = LogFactory.getLog(SftpUtil.class);
  //private static final NYConfig NY_CONFIG = NYConfig.getConfig();

  private SftpUtil(){}

  /**
   * 创建sftp
   * @param sshHost
   * @param sshPort
   * @param sshUser
   * @param sshPass
   * @return
   */
  public static Sftp login(String sshHost, int sshPort, String sshUser, String sshPass){
    return new Sftp(sshHost,sshPort,sshUser,sshPass);
  }

//  public static Sftp login(){
//    return new Sftp(NY_CONFIG);
//  }

  /**
   * 获得一个SSH会话，重用已经使用的会话,从JschSessionPool中获取
   * @param sshHost 主机
   * @param sshPort 端口
   * @param sshUser 用户名
   * @param sshPass 密码
   * @return SSH会话
   */
  public static Session getSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    return JschSessionPool.INSTANCE.getSession(sshHost, sshPort, sshUser, sshPass);
  }

  /**
   * 创建一个ssh会话,此方法并不打开会话（即不调用connect方法）
   * @param sshHost ip
   * @param sshPort 端口
   * @param sshUser 用户
   * @param sshPass 密码
   * @param privateKeyPath 私钥路径
   * @param passphrase 私钥密码.可以为null
   * @return
   */
  public static Session createSession(String sshHost, int sshPort, String sshUser, String sshPass,String privateKeyPath,byte[] passphrase){
    //默认为一账通给定的账户
    if (StrUtil.isEmpty(sshUser)){
      sshUser = "gdnybank";
    }

    final JSch jsch = new JSch();
    Session session = null;
    try {
      // 设置私钥,目前没有用到
      if (!StrUtil.isEmpty(privateKeyPath)){
        jsch.addIdentity(privateKeyPath,passphrase);
      }
      session = jsch.getSession(sshUser,sshHost,sshPort);
      // 设置密码
      if (!StrUtil.isEmpty(sshPass)){
        session.setPassword(sshPass);
      }
      // 设置第一次登录的时候提示，可选值：(ask | yes | no)
      session.setConfig("StrictHostKeyChecking","no");
    } catch (JSchException e) {
      log.error("createSession error {}",e);
    }
    return session;
  }

  /**
   * 打开一个新的SSH会话
   * @param sshHost 主机
   * @param sshPort 端口
   * @param sshUser 用户名
   * @param sshPass 密码
   * @return SSH会话
   */
  public static Session openSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    final Session session = createSession(sshHost,sshPort,sshUser,sshPass,null,null);
    try {
      session.connect();
    } catch (JSchException e) {
      log.error("openSession error {}",e);
    }
    return session;
  }

  /**
   * 打开Channel连接
   * @param session     Session会话
   * @return {@link Channel}
   */
  public static Channel openChannel(Session session){
    final Channel channel = createChannel(session);
    try {
      channel.connect();
    } catch (JSchException e) {
      log.error("openChannel error {}",e);
    }
    return channel;
  }

  /**
   * 创建Channel连接
   * @param session     Session会话
   * @return {@link Channel}
   */
  public static Channel createChannel(Session session){
    Channel channel = null;
    try {
      if (Boolean.FALSE == session.isConnected()){
        session.connect();
      }
      channel = session.openChannel("sftp");
    } catch (JSchException e) {
      log.error("createChannel error {}",e);
    }
    return channel;

  }

  /**
   * 关闭sftp连接的会话和通道
   * @param channel
   * @param session
   */
  public static void close(Channel channel,Session session){
    if (null != session && session.isConnected()){
      JschSessionPool.INSTANCE.remove(session);
    }
    if (null != channel && channel.isConnected()){
      channel.disconnect();
    }
  }

  /** sftp **/
  private static class Sftp{
    private Session session;
    private ChannelSftp sftp;
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final Log log = LogFactory.getLog(Sftp.class);

//    public Sftp(NYConfig config){
//      init(config);
//    }

    public Sftp(String sshHost, int sshPort, String sshUser, String sshPass){
      init(sshHost,sshPort,sshUser,sshPass);
    }

//    /**
//     * 初始化:使用南粤银行里面的配置信息进行初始化
//     * @param config
//     */
//    public void init(NYConfig config){
//      Session session = SftpUtil.getSession(config.getSshHost(), config.getSshPort(), config.getSshUser(), config.getSshPass());
//      this.session = session;
//      initChannel(this.session);
//    }

    /**
     * 初始化,使用自定义的账号密码
     * @param sshHost
     * @param sshPort
     * @param sshUser
     * @param sshPass
     */
    public void init(String sshHost, int sshPort, String sshUser, String sshPass){
      Session session = SftpUtil.getSession(sshHost,sshPort,sshUser,sshPass);
      this.session = session;
      initChannel(this.session);
    }

    /**
     * 初始化通道
     * @param session
     */
    public void initChannel(Session session){
      try {
        ChannelSftp channel = (ChannelSftp) SftpUtil.openChannel(session);
        channel.setFilenameEncoding(DEFAULT_CHARSET.toString());
        this.sftp = channel;
      } catch (SftpException e) {
        log.error("Sftp initChannel error {}",e);
      }
    }

    /**
     * 创建文件夹
     * @param dir
     * @return
     */
    public boolean mkdir(String dir) {
      try {
        this.sftp.mkdir(dir);
        return true;
      } catch (SftpException e) {
        log.error("Sftp mkdir error {}",e);
        return false;
      }
    }

    /**
     * 打开指定目录，如果指定路径非目录或不存在返回false
     * @param directory directory
     * @return 是否打开目录
     */
    public boolean cd(String directory) {
      if (StrUtil.isEmpty(directory)) {
        // 当前目录
        return true;
      }
      try {
        sftp.cd(directory.replaceAll("\\\\", "/"));
        return true;
      } catch (SftpException e) {
        log.error("Sftp cd error {}",e);
        return false;
      }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     * @param is 输入流
     * @param directory 上传目录
     * @param sftpFileName 上传文件名称
     */
    public void upload(InputStream is,String directory,String sftpFileName){
      boolean cd = cd(directory);
      if (!cd){
        //目录不存在,需要创建
        mkdir(directory);
        cd(directory);
      }
      try {
        sftp.put(is,sftpFileName);//默认是覆盖模式
      } catch (SftpException e) {
        log.error("Sftp upload error {}",e);
      }
    }

    /**
     * 将bytes上传到sftp
     * @param bytes 字节数组
     * @param directory 上传目录
     * @param sftpFileName 上传文件名称
     */
    public void upload(byte[] bytes,String directory,String sftpFileName){
      upload(new ByteArrayInputStream(bytes),directory,sftpFileName);
    }

    /**
     * 将字符串按照指定的字符编码上传到sftp
     * @param data 字符串
     * @param directory 上传目录
     * @param sftpFileName 上传文件名称
     */
    public void upload(String data,String directory,String sftpFileName){
      upload(new ByteArrayInputStream(data.getBytes(DEFAULT_CHARSET)),directory,sftpFileName);
    }

    // todo 上传本地文件到服务器上
//    public void upload(String data,String directory,String sftpFileName){
//
//    }

    /**
     * 遍历某个目录下所有文件或目录，生成列表，不会递归遍历<br>
     * 此方法自动过滤"."和".."两种目录
     * @param path 遍历目录
     * @return
     */
    public List<String> ls(String path){
      final List<String> ls = new ArrayList<>();
      try {
        sftp.ls(path,entry -> {
          String filename = entry.getFilename();
          SftpATTRS attrs = entry.getAttrs();
          ls.add(filename);
          return ChannelSftp.LsEntrySelector.CONTINUE;
        });
      } catch (SftpException e) {
        log.error("ls error {}",e);
      }
      return ls;
    }


    public void close(){
      SftpUtil.close(this.sftp,this.session);
    }
  }

  /** jsch会话池**/
  private enum JschSessionPool{

    INSTANCE;

    /**
     * 防止并发问题,使用ConcurrentHashMap,所以不用重入锁进行锁了
     */
    private final ConcurrentHashMap<String,Session> cache = new ConcurrentHashMap<>();

    /**
     * 获取session,不存在返回null
     * @param key
     * @return
     */
    public Session get(String key){
      return this.cache.get(key);
    }

    /**
     * 获得一个session,重用已经存在的session会话
     * @param sshHost
     * @param sshPort
     * @param sshUser
     * @param sshPass
     * @return
     */
    public Session getSession(String sshHost, int sshPort, String sshUser, String sshPass){
      // user@host:port
      final String key = sshUser+"@"+sshHost+":"+sshPort;
      Session session = SftpUtil.openSession(sshHost, sshPort, sshUser, sshPass);
      this.cache.put(key,session);
      return session;
    }

    /**
     * 关闭SSH连接会话
     * @param key
     */
    public void close(String key){
      Session session = this.cache.get(key);
      if (null != session && session.isConnected()){
        session.disconnect();
      }
      this.cache.remove(key);
    }

    /**
     * 移除指定session entrySet比keySet速度快
     * @param session
     */
    public void remove(Session session){
      if (null != session){
        Iterator<Map.Entry<String, Session>> iterator = this.cache.entrySet().iterator();
        while (iterator.hasNext()){
          Map.Entry<String, Session> next = iterator.next();
          if (session == next.getValue()){
              session.disconnect();
            iterator.remove();
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Sftp sftp = SftpUtil.login("123.56.106.192",22,"root","Fdkj2020");

    boolean cd = sftp.cd("/gdnybank");
    if(cd){
      System.out.println("进入目录成功");
    }else {
      System.out.println("进入目录失败");
    }

    List<String> ls = sftp.ls("/root");
    ls.forEach(System.out::println);

    sftp.close();

    //cn.hutool.extra.ssh.Sftp sftp1 = JschUtil.createSftp("101.89.104.151", 5422, "gdnybank", "Gdbank.1234");


  }

}
