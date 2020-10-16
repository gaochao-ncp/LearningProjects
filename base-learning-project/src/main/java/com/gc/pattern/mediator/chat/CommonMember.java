package com.gc.pattern.mediator.chat;

/**
 * @author gaochao
 * @create 2020-10-10 11:33
 */
public class CommonMember extends AbstractMember {

  public CommonMember(AbstractChatRoom chatRoom, String name) {
    super(chatRoom, name);
  }

  public CommonMember(String name) {
    super(name);
  }

  @Override
  public void sendText(String to, String message) {
    chatRoom.sendText(name,to,message);
  }

  @Override
  public void sendImage(String to, String image) {
    System.out.println("普通会员无法发送图片");
  }
}
