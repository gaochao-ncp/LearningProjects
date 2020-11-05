package com.gc.date;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2020-10-19 15:34
 * @version: 1.0
 */
@Data
public class Test {

  private static final Log log = LogFactory.getLog(Test.class);
  private boolean started = false;

  private static final Map<Integer,String> FIELD_MAPPING = new HashMap<>();

  static {
    FIELD_MAPPING.put(1,"channelNo");
    FIELD_MAPPING.put(2,"sysNo");
    FIELD_MAPPING.put(3,"sysTrace");
    FIELD_MAPPING.put(4,"busiTrace");
    FIELD_MAPPING.put(5,"transBranCode");
    FIELD_MAPPING.put(6,"transDate");
    FIELD_MAPPING.put(7,"transTime");
    FIELD_MAPPING.put(8,"setDate");
    FIELD_MAPPING.put(9,"operNo");
    FIELD_MAPPING.put(10,"authNo");
    FIELD_MAPPING.put(11,"seqNo");
    FIELD_MAPPING.put(12,"transCode");
    FIELD_MAPPING.put(13,"acctNo");
    FIELD_MAPPING.put(14,"cardNo");
    FIELD_MAPPING.put(15,"vouType");
    FIELD_MAPPING.put(16,"vouNo");
    FIELD_MAPPING.put(17,"transSrc");
    FIELD_MAPPING.put(18,"curCode");
    FIELD_MAPPING.put(19,"curType");
    FIELD_MAPPING.put(20,"dcFlag");
    FIELD_MAPPING.put(21,"transAmt");
    FIELD_MAPPING.put(22,"acctAmt");
    FIELD_MAPPING.put(23,"fee");
    FIELD_MAPPING.put(24,"acctBal");
    FIELD_MAPPING.put(25,"oppAcctNo");
    FIELD_MAPPING.put(26,"oppAcctName");
    FIELD_MAPPING.put(27,"oppBankNo");
    FIELD_MAPPING.put(28,"oppBankName");
    FIELD_MAPPING.put(29,"validFlag");
    FIELD_MAPPING.put(30,"abs");
    FIELD_MAPPING.put(31,"remark");
    FIELD_MAPPING.put(32,"remark1");
    FIELD_MAPPING.put(33,"postScript");
  }

  public static void main(String[] args) {
    invoke();

  }


  public static void invoke()  {
    Map<String,Object> params = new HashMap<>();
    String files = "000008|030036|0300362032051300107962|0300362032051300107962|1000|2032-05-15|130433|2032-05-15|B0009806||54946081|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|9000.00|9000.00|0.00|24500.00|100001236000014859|刘刚|1000|总行营业部||本金兑付||||\n" +
            "000008|030036|0300362032051300107962|0300362032051300107962|1000|2032-05-15|130433|2032-05-15|B0009806||54946097|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|6000.00|6000.00|0.00|30500.00|100001236000014792|刘刚|1000|总行营业部||本金兑付||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946505|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|10.31|10.31|0.00|30510.31|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946506|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|15.63|15.63|0.00|30525.94|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946514|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|25.00|25.00|0.00|30550.94|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946518|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|21.88|21.88|0.00|30572.82|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946519|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|30.00|30.00|0.00|30602.82|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032051300107973|0300362032051300107973|1000|2032-05-15|130638|2032-05-15|B0009806||54946521|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|26.25|26.25|0.00|30629.07|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000021|030036|0300362032051500108023|0102082032051544514313|1000|2032-05-15|155818|2032-05-15|W62001||54985406|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|9500.00|9500.00|0.00|21129.07|100001236000014933|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032051500108025|0102082032051586412950|1000|2032-05-15|160101|2032-05-15|W62001||54986129|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|9500.00|9500.00|0.00|30629.07|100001236000014933|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000021|030003|0300032032051501861547|0102082032052035241576|1000|2032-05-20|190229|2032-05-20|E1000||55290502|IB0010|100001235000200856|6235956022000020804|999|0102082032051500070186||CNY|0|1|150.00|150.00|0.00|27488.07|6214832807981159|刘刚|3085840000|招商银行||小额汇款||||\n" +
            "000008|030036|0300362032061300108688|0300362032061300108688|1000|2032-06-19|180405|2032-06-19|B0009806||55560803|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|10.66|10.66|0.00|27498.73|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032061300108688|0300362032061300108688|1000|2032-06-19|180405|2032-06-19|B0009806||55560804|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|16.14|16.14|0.00|27514.87|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032061300108688|0300362032061300108688|1000|2032-06-19|180405|2032-06-19|B0009806||55560811|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|22.60|22.60|0.00|27537.47|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032061300108688|0300362032061300108688|1000|2032-06-19|180405|2032-06-19|B0009806||55560812|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|31.00|31.00|0.00|27568.47|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032061600108752|0300362032061600108752|1000|2032-06-19|181159|2032-06-19|B0009806||55562548|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|10.66|10.66|0.00|27579.13|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000021|030036|0300362032063000109029|0102082032063057549174|1000|2032-06-30|135835|2032-06-30|W62001||55783020|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|4005.00|4005.00|0.00|23574.13|100001236000015351|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032063000109030|0102082032063029374471|1000|2032-06-30|135932|2032-06-30|W62001||55783201|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|4006.00|4006.00|0.00|19568.13|100001236000015369|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032063000109031|0102082032063041135147|1000|2032-06-30|140148|2032-06-30|W62001||55783594|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|5000.00|5000.00|0.00|14568.13|100001236000015377|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032063000109032|0102082032063068296448|1000|2032-06-30|140257|2032-06-30|W62001||55783808|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|6000.00|6000.00|0.00|8568.13|100001236000015385|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032063000109036|0102082032063054432301|1000|2032-06-30|141116|2032-06-30|W62001||55785628|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|6000.00|6000.00|0.00|14568.13|100001236000015385|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000021|030036|0300362032063000109041|0102082032063071946160|1000|2032-06-30|142624|2032-06-30|W62001||55788643|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|4005.00|4005.00|0.00|18573.13|100001236000015351|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000021|030036|0300362032070100109077|0102082032070117216649|1000|2032-07-01|165829|2032-07-01|W62001||55820613|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1400.00|1400.00|0.00|17173.13|100001236000015484|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070100109078|0102082032070114041016|1000|2032-07-01|165922|2032-07-01|W62001||55820791|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1500.00|1500.00|0.00|15673.13|100001236000015492|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070100109079|0102082032070184101177|1000|2032-07-01|170307|2032-07-01|W62001||55821643|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|1500.00|1500.00|0.00|17173.13|100001236000015492|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000021|030036|0300362032070200109117|0102082032070236797473|1000|2032-07-02|094306|2032-07-02|W62001||56007435|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1500.00|1500.00|0.00|15673.13|100001236000015534|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070200109118|0102082032070257983209|1000|2032-07-02|094703|2032-07-02|W62001||56008290|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1600.00|1600.00|0.00|14073.13|100001236000015542|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070200109119|0102082032070229808893|1000|2032-07-02|100725|2032-07-02|W62001||56012418|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1700.00|1700.00|0.00|12373.13|100001236000015559|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070200109120|0102082032070214117211|1000|2032-07-02|101047|2032-07-02|W62001||56013106|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1800.00|1800.00|0.00|10573.13|100001236000015567|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070200109121|0102082032070235086975|1000|2032-07-02|101120|2032-07-02|W62001||56013214|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|1|1900.00|1900.00|0.00|8673.13|100001236000015575|刘刚|1000|总行营业部||转存||||\n" +
            "000021|030036|0300362032070200109123|0102082032070241585090|1000|2032-07-02|101423|2032-07-02|W62001||56013830|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|1600.00|1600.00|0.00|10273.13|100001236000015542|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000021|030036|0300362032070200109124|0102082032070298509845|1000|2032-07-02|101940|2032-07-02|W62001||56014878|IB0010|100001235000200856|6235956022000020804|0|||CNY|0|2|1800.00|1800.00|0.00|12073.13|100001236000015567|刘刚|1000|总行营业部||定期存款转活期||||\n" +
            "000008|030036|0300362032070300109149|0300362032070300109149|1000|2032-07-03|114048|2032-07-03|B0009806||56031610|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|4006.00|4006.00|0.00|16079.13|100001236000015369|刘刚|1000|总行营业部||本金兑付||||\n" +
            "000008|030036|0300362032070300109158|0300362032070300109158|1000|2032-07-03|114221|2032-07-03|B0009806||56031917|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|2.00|2.00|0.00|16081.13|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032070400109177|0300362032070400109177|1000|2032-07-04|115638|2032-07-04|B0009806||56035023|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|1900.00|1900.00|0.00|17981.13|100001236000015575|刘刚|1000|总行营业部||本金兑付||||\n" +
            "000008|030036|0300362032070400109186|0300362032070400109186|1000|2032-07-04|115813|2032-07-04|B0009806||56035305|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|0.79|0.79|0.00|17981.92|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n" +
            "000008|030036|0300362032070500109227|0300362032070500109227|1000|2032-07-05|123523|2032-07-05|B0009806||56317647|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|1700.00|1700.00|0.00|19681.92|100001236000015559|刘刚|1000|总行营业部||本金兑付||||\n" +
            "000008|030036|0300362032070500109236|0300362032070500109236|1000|2032-07-05|123658|2032-07-05|B0009806||56317947|IB0062|100001235000200856|6235956022000020804|0||8|CNY|0|2|0.85|0.85|0.00|19682.77|10000126107090001|个人定期存款应付利息-智能存款|1000|总行营业部||结息||||\n";
    log.info("files detail is ===> ["+files+"]");

    if (StringUtils.isNotBlank(files)){
      String[] filesArr = files.split("\n");
      List<FileDetailDTO> fileDetailDTOList = new ArrayList<>();
      //解析文件数组
      for (String file : filesArr) {
        String[] fileDetailDTOArr = file.split("\\|");
        FileDetailDTO fileDetailDTO = new FileDetailDTO();
        for (int i = 0; i < fileDetailDTOArr.length; i++) {
          resolve(fileDetailDTO,FIELD_MAPPING.get(i+1),fileDetailDTOArr[i]);
        }
        fileDetailDTOList.add(fileDetailDTO);
      }
      //处理返回的数据,计算总额
      List<Double> list = new ArrayList<>();
      if (null != fileDetailDTOList && fileDetailDTOList.size()>0){
        fileDetailDTOList.stream().forEach(f ->{
          list.add(f.getTransAmt());
        });
      }
      params.put("TRANS_AMT_LIST",list);
      //计算所有列表的总和
      double count = list.stream().mapToDouble(Number::doubleValue).sum();
      params.put("TRANS_AMT_COUNT",count);
    }

  }





  /**
   * 解析数组到实体类中去
   * @param t
   * @param fieldName
   * @param value
   */
  private static void resolve(FileDetailDTO t,String fieldName,Object value){
    Field field ;
    try {
      field = t.getClass().getDeclaredField(fieldName);
      field.setAccessible(Boolean.TRUE);
      if (Double.class.getName().equals(field.getGenericType().getTypeName())){
        field.set(t,Double.valueOf(value.toString()));
      }else if (Long.class.getName().equals(field.getGenericType().getTypeName())){
        field.set(t,Long.valueOf(value.toString()));
      }else {
        field.set(t,value.toString());
      }
    } catch (Exception e) {
      log.error("resolve FileDetailDTO error ==> {}",e);
    }
  }

  /**
   * @author: Administrator
   * @date: 2020-10-19 14:24
   * @version: 1.0
   */
  public static class FileResponseDTO {

    /**
     * 客户编号
     */
    private String custNo;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 账户机构
     */
    private String acctBranCode;
    /**
     * 账户余额
     */
    private Double acctBal;
    /**
     * 记录数
     */
    private Long records;
    /**
     * 文件名
     */
    private String fileName;

    public String getCustNo() {
      return custNo;
    }

    public void setCustNo(String custNo) {
      this.custNo = custNo;
    }

    public String getCustName() {
      return custName;
    }

    public void setCustName(String custName) {
      this.custName = custName;
    }

    public String getAcctBranCode() {
      return acctBranCode;
    }

    public void setAcctBranCode(String acctBranCode) {
      this.acctBranCode = acctBranCode;
    }

    public Double getAcctBal() {
      return acctBal;
    }

    public void setAcctBal(Double acctBal) {
      this.acctBal = acctBal;
    }

    public Long getRecords() {
      return records;
    }

    public void setRecords(Long records) {
      this.records = records;
    }

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }
  }

  /**
   * @author: Administrator
   * @date: 2020-10-19 14:23
   * @version: 1.0
   */
  public static class FileDetailDTO{
    //文件格式
    private Double acctBal;
    private String channelNo;
    private String sysNo;
    private String sysTrace;
    private String busiTrace;
    private String transBranCode;
    private String transDate;
    private String transTime;
    private String setDate;
    private String operNo;
    private String authNo;
    private Long seqNo;
    private String transCode;
    private String acctNo;
    private String cardNo;
    private String vouType;
    private String vouNo;
    private String transSrc;
    private String curCode;
    private String curType;
    private String dcFlag;
    private Double transAmt;
    private Double acctAmt;
    private Double fee;
    private String oppAcctNo;
    private String oppAcctName;
    private String oppBankNo;
    private String oppBankName;
    private String validFlag;
    private String abs;
    private String remark;
    private String remark1;
    private String postScript;

    public Double getAcctBal() {
      return acctBal;
    }

    public void setAcctBal(Double acctBal) {
      this.acctBal = acctBal;
    }

    public String getChannelNo() {
      return channelNo;
    }

    public void setChannelNo(String channelNo) {
      this.channelNo = channelNo;
    }

    public String getSysNo() {
      return sysNo;
    }

    public void setSysNo(String sysNo) {
      this.sysNo = sysNo;
    }

    public String getSysTrace() {
      return sysTrace;
    }

    public void setSysTrace(String sysTrace) {
      this.sysTrace = sysTrace;
    }

    public String getBusiTrace() {
      return busiTrace;
    }

    public void setBusiTrace(String busiTrace) {
      this.busiTrace = busiTrace;
    }

    public String getTransBranCode() {
      return transBranCode;
    }

    public void setTransBranCode(String transBranCode) {
      this.transBranCode = transBranCode;
    }

    public String getTransDate() {
      return transDate;
    }

    public void setTransDate(String transDate) {
      this.transDate = transDate;
    }

    public String getTransTime() {
      return transTime;
    }

    public void setTransTime(String transTime) {
      this.transTime = transTime;
    }

    public String getSetDate() {
      return setDate;
    }

    public void setSetDate(String setDate) {
      this.setDate = setDate;
    }

    public String getOperNo() {
      return operNo;
    }

    public void setOperNo(String operNo) {
      this.operNo = operNo;
    }

    public String getAuthNo() {
      return authNo;
    }

    public void setAuthNo(String authNo) {
      this.authNo = authNo;
    }

    public Long getSeqNo() {
      return seqNo;
    }

    public void setSeqNo(Long seqNo) {
      this.seqNo = seqNo;
    }

    public String getTransCode() {
      return transCode;
    }

    public void setTransCode(String transCode) {
      this.transCode = transCode;
    }

    public String getAcctNo() {
      return acctNo;
    }

    public void setAcctNo(String acctNo) {
      this.acctNo = acctNo;
    }

    public String getCardNo() {
      return cardNo;
    }

    public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
    }

    public String getVouType() {
      return vouType;
    }

    public void setVouType(String vouType) {
      this.vouType = vouType;
    }

    public String getVouNo() {
      return vouNo;
    }

    public void setVouNo(String vouNo) {
      this.vouNo = vouNo;
    }

    public String getTransSrc() {
      return transSrc;
    }

    public void setTransSrc(String transSrc) {
      this.transSrc = transSrc;
    }

    public String getCurCode() {
      return curCode;
    }

    public void setCurCode(String curCode) {
      this.curCode = curCode;
    }

    public String getCurType() {
      return curType;
    }

    public void setCurType(String curType) {
      this.curType = curType;
    }

    public String getDcFlag() {
      return dcFlag;
    }

    public void setDcFlag(String dcFlag) {
      this.dcFlag = dcFlag;
    }

    public Double getTransAmt() {
      return transAmt;
    }

    public void setTransAmt(Double transAmt) {
      this.transAmt = transAmt;
    }

    public Double getAcctAmt() {
      return acctAmt;
    }

    public void setAcctAmt(Double acctAmt) {
      this.acctAmt = acctAmt;
    }

    public Double getFee() {
      return fee;
    }

    public void setFee(Double fee) {
      this.fee = fee;
    }

    public String getOppAcctNo() {
      return oppAcctNo;
    }

    public void setOppAcctNo(String oppAcctNo) {
      this.oppAcctNo = oppAcctNo;
    }

    public String getOppAcctName() {
      return oppAcctName;
    }

    public void setOppAcctName(String oppAcctName) {
      this.oppAcctName = oppAcctName;
    }

    public String getOppBankNo() {
      return oppBankNo;
    }

    public void setOppBankNo(String oppBankNo) {
      this.oppBankNo = oppBankNo;
    }

    public String getOppBankName() {
      return oppBankName;
    }

    public void setOppBankName(String oppBankName) {
      this.oppBankName = oppBankName;
    }

    public String getValidFlag() {
      return validFlag;
    }

    public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
    }

    public String getAbs() {
      return abs;
    }

    public void setAbs(String abs) {
      this.abs = abs;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRemark1() {
      return remark1;
    }

    public void setRemark1(String remark1) {
      this.remark1 = remark1;
    }

    public String getPostScript() {
      return postScript;
    }

    public void setPostScript(String postScript) {
      this.postScript = postScript;
    }
  }
}
