package com.gc.delaytask;

import ch.qos.logback.core.db.dialect.DBUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 具体业务逻辑处理类
 *
 * @author: Administrator
 * @date: 2020-10-20 17:55
 * @version: 1.0
 */
public class NotifyTask extends AbstractNotifyTask {

  public NotifyTask() {
  }

  public NotifyTask(NotifyRecord notifyRecord,NotifyParam notifyParam) {
    super(notifyRecord,notifyParam);
  }

  @Override
  public boolean doTask(NotifyRecord notifyRecord,NotifyParam notifyParam) {
    System.out.println("执行时间:"+ DateUtil.now() +";ID:"+notifyParam.getId());
    JSONObject responseJson = mokJson(notifyParam.getId(),notifyRecord);
    if (null != responseJson && null != (responseJson.getJSONObject("body"))){
      // 交易状态:0-成功;1-失败;2-处理中;3-交易有缺陷成功
      String TRXSTATUS = responseJson.getJSONObject("body").getString("TRXSTATUS");
      String SYSRTNCD = responseJson.getJSONObject("body").getString("SYSRTNCD");
      //查询成功标志
      if ("00000000".equals(SYSRTNCD)){
        if (!"2".equals(TRXSTATUS)){
          //状态不为 处理中 的时候不再轮询,进行入库操作
          if ("0".equals(TRXSTATUS)){
            //TODO 调用转账子流程

          }
          return true;
        }
      }
    }else {
      // 执行失败的逻辑
      Utils.print(notifyRecord.getTaskId(), "开始执行任务,发送通知失败");
    }
    return false;
  }


  private JSONObject mokJson(String id, NotifyRecord record){
    String text = "";
    if ("0".equals(id)){
      text = "{\"Head\":{},\t\"body\":{\"SYSRTNCD\":\"00000000\",\"TRXSTATUS\":\"0\"}}";
    }else if ("1".equals(id)){
      text = "{\"Head\":{},\t\"body\":{\"SYSRTNCD\":\"00000000\",\"TRXSTATUS\":\"1\"}}";
    }else if ("2".equals(id)){
      //轮询三次后变为成功
      if (record.getVersion() == 3){
        text = "{\"Head\":{},\t\"body\":{\"SYSRTNCD\":\"00000000\",\"TRXSTATUS\":\"0\"}}";
      }else {
        text = "{\"Head\":{},\t\"body\":{\"SYSRTNCD\":\"00000000\",\"TRXSTATUS\":\"2\"}}";
      }
    }else if ("3".equals(id)){
      text = "{\"Head\":{},\t\"body\":{\"SYSRTNCD\":\"00000000\",\"TRXSTATUS\":\"3\"}}";
    }
    return JSONObject.parseObject(text, JSONObject.class);
  }
}
