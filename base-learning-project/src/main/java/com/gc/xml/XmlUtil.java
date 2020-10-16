package com.gc.xml;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.gc.xml.pfrom.IdentifySingleCheckBody;
import com.gc.xml.pfrom.Root;
import com.gc.xml.pfrom.SdoRoot;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多层级map和xml字符串的互相转换关系
 * @author gaochao
 * @create 2020-09-18 14:44
 */
@Slf4j
public class XmlUtil {
  static Map<String, String> xmlMap = new HashMap<String, String>();

  public static void main(String[] args) {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <SDOROOT package_type=\"xml\"> \t<SYS_HEAD> \t\t<ORG_SYS_ID>010208</ORG_SYS_ID> \t\t<ESB_SEQ_NO>C1-20200918104524-015601</ESB_SEQ_NO> \t\t<SERVICE_SCENE>01</SERVICE_SCENE> \t\t<TRAN_TIMESTAMP>104531718</TRAN_TIMESTAMP> \t\t<RET_STATUS>S</RET_STATUS> \t\t<SERVICE_CODE>11002000181</SERVICE_CODE> \t\t<CONSUMER_ID>010208</CONSUMER_ID> \t\t<RET> \t\t\t<SDO> \t\t\t\t<RET_MSG>交易成功完成</RET_MSG> \t\t\t\t<RET_CODE>I010209000000</RET_CODE> \t\t\t</SDO> \t\t</RET> \t\t<TRAN_DATE>20200918</TRAN_DATE> \t\t<CONSUMER_SEQ_NO>0102082032111268954053</CONSUMER_SEQ_NO> \t</SYS_HEAD> \t<APP_HEAD> \t\t<USER_ID>W62001</USER_ID> \t</APP_HEAD> \t<LOCAL_HEAD> \t\t<RURAL_BRANCH_ID>0000</RURAL_BRANCH_ID> \t</LOCAL_HEAD> \t<BODY> \t\t<MSG_FLOW>00000000000102082032111235721146</MSG_FLOW> \t\t<TRAN_LIST_ARRAY> \t\t\t<SDO> \t\t\t\t<ID>511523199412176453</ID> \t\t\t\t<PHOTO></PHOTO> \t\t\t\t<NAME>刘刚</NAME> \t\t\t\t<ISSUE_OFFICE>辽宁0</ISSUE_OFFICE> \t\t\t\t<CHECK_RESULT>00</CHECK_RESULT> \t\t\t</SDO> \t\t</TRAN_LIST_ARRAY> \t\t<ENTRUST_DATE>20321112104520</ENTRUST_DATE> \t\t<VER>1.0</VER> \t\t<STS>0000</STS> \t\t<MSG_TYPE>picp.250.002.01</MSG_TYPE> \t\t<RESERVE>String</RESERVE> \t</BODY> </SDOROOT>";
    Map<String, Object> map = multilayerXmlToMap(xml);
    Root root = JSONObject.parseObject(JSONObject.toJSONString(map), Root.class);
    //解析body部分
    String json = "{\"SYS_HEAD\":{\"ESB_SEQ_NO\":\"\",\"CONSUMER_SVR_ID\":\"\",\"USER_LANG\":\"\",\"TRAN_DATE\":\"20200929\",\"WS_ID\":\"\",\"ORG_SYS_ID\":\"010208\",\"FILE_PATH\":\"\",\"SERVICE_SCENE\":\"02\",\"TRAN_TIMESTAMP\":\"112225722\",\"SERVICE_CODE\":\"11002000182\",\"RET_STATUS\":\"S\",\"CONSUMER_ID\":\"010208\",\"RET\":{\"SDO\":{\"RET_MSG\":\"交易成功完成\",\"RET_CODE\":\"I030006000000\"}},\"MODULE_ID\":\"\",\"PROGRAM_ID\":\"\",\"CONSUMER_SEQ_NO\":\"0102082020092960666959\"},\"APP_HEAD\":{\"APPR_USER_ID_ARRAY\":\"\",\"USER_LEVEL\":\"\",\"USER_TYPE\":\"\",\"REVERSAL_SEQ_NO\":\"\",\"AUTH_FLAG\":\"\",\"PGUP_OR_PGDN\":\"\",\"BRANCH_ID\":\"1000\",\"BIZ_SEQ_NO\":\"\",\"QUERY_KEY\":\"\",\"USER_PASSWORD\":\"\",\"USER_ID\":\"W62001\",\"REVERSAL_DATE\":\"\",\"APPR_FLAG\":\"\",\"AUTH_USER_ID_ARRAY\":\"\",\"PER_PAGE_NUM\":\"\"},\"LOCAL_HEAD\":{\"KEY_DATE\":\"\",\"LINK_FLAG\":\"\",\"CHANNEL_CODE\":\"000021\",\"BUS_SEQ_NO\":\"0102082020092960666959\",\"KEY_FACTOR\":\"\",\"KEY_NAME\":\"\",\"KEY_TYPE\":\"\",\"RURAL_BRANCH_ID\":\"0000\",\"TRANS_TYPE\":\"\",\"LINK_TRANS_CODE\":\"\"},\"BODY\":{\"NATION\":\"汉\",\"ID_NUM\":\"130622199911227814\",\"ERROR_CODE\":\"0\",\"EXPIRY_DATE\":\"20190215-20290215\",\"ISSUE_AUTHORITY\":\"保定市清苑区公安局\",\"SEX\":\"男\",\"NAME\":\"张恩瑞\",\"ADDRESS\":\"河北省保定市清苑区新华街新建胡同130号\",\"HEAD_IMAGE\":\"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\",\"BIRTH\":\"19991122\"}}";

    SdoRoot bb = JSONObject.parseObject(json, SdoRoot.class);

    IdentifySingleCheckBody zz = JSONObject.parseObject(JSONObject.toJSONString(root.getSdoRoot().getBody()),IdentifySingleCheckBody.class);
    root.getSdoRoot().setBody(zz);

    IdentifySingleCheck identifySingleCheck1 = resolveMapToBean(map, IdentifySingleCheck.class);
    System.out.println(identifySingleCheck1.toString());

  }

  public static <T> T resolveMapToBean(Map<String,Object> map,Class<T> clz){

//    Set<Map.Entry<String, Object>> entries = map.entrySet();
//    Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
//    while (iterator.hasNext()){
//      Map.Entry<String, Object> next = iterator.next();
//      String key = next.getKey();
//      Object value = next.getValue();
//      if ("SYS_HEAD".equals(key)){
//        System.out.println("SYS_HEAD="+value.toString());
//      }else if ("BODY".equals(key)){
//        System.out.println("BODY="+value.toString());
//      }else if ("RET_MSG".equals(key)){
//        System.out.println("RET_MSG="+value.toString());
//      }else if ("RET_CODE".equals(key)){
//        System.out.println("RET_CODE="+value.toString());
//      }
//    }

    Map<String, Object> headMap = new HashMap<>();
    Map<String, Object> bodyMap = new HashMap<>();
    map = (Map<String, Object>) map.get("SDOROOT");
    bodyMap = (Map<String, Object>) map.get("BODY");
    headMap = (Map<String, Object>)((Map<String, Object>) ((Map<String, Object>) map.get("SYS_HEAD")).get("RET")).get("SDO");
    String RET_MSG = headMap.get("RET_MSG").toString();
    String RET_CODE = headMap.get("RET_CODE").toString();


    String jsonString = JSONObject.toJSONString(bodyMap);
    T t = JSONObject.parseObject(jsonString, clz);
    try {
//      Field field = t.getClass().getDeclaredField("retCd");
//      field.set(t,RET_MSG);

      Method M1 = t.getClass().getDeclaredMethod("setRet",String.class,String.class);
      M1.setAccessible(true) ;
      M1.invoke(t,RET_MSG,RET_CODE);
//      Method M2 = t.getClass().getDeclaredMethod("setRetMsg",String.class);
//      M2.setAccessible(true) ;
//      M2.invoke(t,RET_CODE);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return t;
  }


  /**
   * (多层)xml格式字符串转换为map
   *
   * @param xml xml字符串
   * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
   */
  public static Map<String, Object> multilayerXmlToMap(String xml) {
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      log.error("xml字符串解析，失败 --> {}", e);
    }
    Map<String, Object> map = new HashMap<>();
    if (null == doc) {
      return map;
    }
    // 获取根元素
    Element rootElement = doc.getRootElement();
    recursionXmlToMap(rootElement,map);
    return map;
  }

  /**
   * multilayerXmlToMap核心方法，递归调用
   *
   * @param element 节点元素
   * @param outmap 用于存储xml数据的map
   */
  @SuppressWarnings("unchecked")
  private static void recursionXmlToMap(Element element, Map<String, Object> outmap) {
    // 得到根元素下的子元素列表
    List<Element> list = element.elements();
    int size = list.size();
    if (size == 0) {
      // 如果没有子元素,则将其存储进map中
      outmap.put(element.getName(), element.getTextTrim());
    } else {
      // innermap用于存储子元素的属性名和属性值
      Map<String, Object> innermap = new HashMap<>();
      // 遍历子元素
      list.forEach(childElement -> recursionXmlToMap(childElement, innermap));
      outmap.put(element.getName(), innermap);
    }
  }

}
