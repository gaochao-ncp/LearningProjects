package com.gc.mybatis.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

/**
 * 利用Xpath解析XML文档:将inventory.xml中所有图书标题打印出来
 * @author gaochao
 * @create 2020-08-05 15:50
 */
public class XpathTest {

  public static void main(String[] args) throws Exception {
    //创建文件 org.apache.ibatis.parsing.XPathParser.createDocument
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    //开启验证
    documentBuilderFactory.setValidating(Boolean.TRUE);
    documentBuilderFactory.setNamespaceAware(Boolean.FALSE) ;
    documentBuilderFactory.setIgnoringComments(Boolean.TRUE);
    documentBuilderFactory.setIgnoringElementContentWhitespace(Boolean.FALSE);
    documentBuilderFactory .setCoalescing(Boolean.FALSE) ;
    documentBuilderFactory . setExpandEntityReferences(Boolean.TRUE) ;

    //创建documentBuilder
    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
    //设置异常对象处理
    builder.setErrorHandler(new ErrorHandler() {
      @Override
      public void warning(SAXParseException exception) throws SAXException {
        System.out.println ("warning:" + exception.getMessage());
      }

      @Override
      public void error(SAXParseException exception) throws SAXException {
        System.out.println ("error:" + exception.getMessage());
      }

      @Override
      public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println ("fatalError:" + exception.getMessage());
      }
    });
    String properties = System.getProperty("user.dir");
    //将文档加载进document对象中
    Document doc = builder.parse(System.getProperty("user.dir")+"/test-mybatis-demo/src/main/resources/inventory.xml");
    //创建 XPathFactory
    XPathFactory factory = XPathFactory.newInstance();
    //创建 XPath 对象
    XPath xpath = factory.newXPath();

    String txt1 = "===========查询作者为Neal Stevenson 的书的标题============";
    printNodes(xpath,doc,"//book[author='Neal Stevenson']/title/text()",txt1);//[]表示某个节点的值


    String txt2 = "===========查询1997年之后的书的标题============";
    printNodes(xpath,doc,"//book[@year>1997]/title/text()",txt2);//@表示属性


    String txt3 = "===========查询1997年之后的书的标题和属性============";
    printNodes(xpath,doc,"//book[@year>1997]/@*|//book[@year>1997]/title/text()",txt3);

    String txt4 = "===========查询高潮============";
    printNodes(xpath,doc,"//person[name='高潮']/work/text()",txt4);

  }

  /**
   * 打印节点属性
   * @param xpath
   * @param xpath
   * @param expression
   * @param txt
   */
  public static void printNodes(XPath xpath,Document doc,String expression,String txt) throws XPathExpressionException {
    System.out.println(txt+":====================");
    //编译 XPath 表达式
    XPathExpression expr = xpath.compile(expression);
    //类型强制转换
    NodeList nodes  = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
    for (int i = 0; i < nodes.getLength(); i++) {
      System.out.println(nodes.item(i).getNodeName()+"----"+nodes.item(i).getNodeValue());
    }
  }


}
