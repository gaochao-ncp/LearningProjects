package com.gc.pattern.mediator.chat;

/**
 * @author gaochao
 * @create 2020-10-10 11:33
 */
public class SuperMember extends AbstractMember {

  public SuperMember(AbstractChatRoom chatRoom, String name) {
    super(chatRoom, name);
  }

  public SuperMember(String name) {
    super(name);
  }

  @Override
  public void sendText(String to, String message) {
    chatRoom.sendText(name,to,message);
  }

  @Override
  public void sendImage(String to, String image) {
    chatRoom.sendImage(name,to,image);
  }
}
