package com.gc.pattern.mediator.chat;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象中介者:聊天室
 * @author gaochao
 * @create 2020-10-10 11:14
 */
@Data
public abstract class AbstractChatRoom {

  /** 容器:存放用户 **/
  private Map<String,AbstractMember> members = new HashMap<>();

  /**
   * 注册同事
   * @param member
   */
  public void register(AbstractMember member) {
    synchronized (members){
      if (!members.containsValue(member)){
        members.put(member.getName(),member);
      }
    }
  }

  /**
   * 发送消息
   * @param from
   * @param to
   * @param message
   */
  public abstract void sendText(String from, String to, String message);

  /**
   * 发送图片
   * @param from
   * @param to
   * @param image
   */
  public abstract void sendImage(String from ,String to, String image);
}
