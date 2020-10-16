package com.gc.pattern.mediator.chat;

import lombok.Data;

/**
 * 抽象同事
 * @author gaochao
 * @create 2020-10-10 11:15
 */
@Data
public abstract class AbstractMember {

  protected AbstractChatRoom chatRoom;
  protected String name;

  public AbstractMember(AbstractChatRoom chatRoom,String name){
    this.chatRoom=chatRoom;
    this.name=name;
  }

  public AbstractMember(String name){
    this.name=name;
  }

  public abstract void sendText(String to, String message);
  public abstract void sendImage(String to,String image);

  public void receiveText(String from, String message){
    System.out.println(this.name+"接收到"+from+"发来的信息:"+message);
  }

  public void receiveImage(String from, String image){
    System.out.println(this.name+"接收到"+from+"发来的图片:"+image);
  }


}
