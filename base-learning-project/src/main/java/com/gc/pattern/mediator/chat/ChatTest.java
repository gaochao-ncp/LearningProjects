package com.gc.pattern.mediator.chat;

/**
 * @author gaochao
 * @create 2020-10-10 11:43
 */
public class ChatTest {

  public static void main(String[] args) {
    AbstractChatRoom qqChatRoom = new QQChatRoom();
    AbstractChatRoom wxChatRoom = new WXChatRoom();

    AbstractMember commonQ,superQ,commonW,superV;
    commonQ = new CommonMember(qqChatRoom,"TomQQ");
    superQ = new SuperMember(qqChatRoom,"BaiDuQQ");

    qqChatRoom.register(commonQ);
    qqChatRoom.register(superQ);

    commonQ.sendText("BaiDuQQ", "tom say to BaiDuQQ :china no 1");
    commonQ.sendImage("BaiDuQQ", "tom send to BaiDuQQ :china no 1 images");

    superQ.sendText("TomQQ", "BaiDuQQ sends messages to TomQQ ");
    superQ.sendImage("TomQQ", "BaiDuQQ sends images to TomQQ");


    commonW = new CommonMember(wxChatRoom,"WXNick");
    superV = new SuperMember(wxChatRoom,"WXTom");
    wxChatRoom.register(commonW);
    wxChatRoom.register(superV);

    commonW.sendText("WXTom", "WXNick say to WXTom :china no 1");
    commonW.sendImage("WXTom", "WXNick send to WXTom :china no 1 images");

    superV.sendText("WXNick", "WXNick sends messages to TomQQ ");
    superV.sendImage("WXNick", "WXNick sends images to TomQQ");




  }

}
