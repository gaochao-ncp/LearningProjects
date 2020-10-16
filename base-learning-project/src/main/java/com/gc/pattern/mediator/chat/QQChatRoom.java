package com.gc.pattern.mediator.chat;

/**
 * 具体中介者
 * @author gaochao
 * @create 2020-10-10 11:23
 */
public class QQChatRoom extends AbstractChatRoom {

  @Override
  public void sendText(String from, String to, String message) {
    AbstractMember member = super.getMembers().get(to);
    member.receiveText(from,"QQ聊天消息>>>"+message);
  }

  @Override
  public void sendImage(String from, String to, String image) {
    AbstractMember member = super.getMembers().get(to);
    member.receiveImage(from,"QQ聊天图片>>>"+image);
  }
}
