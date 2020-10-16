package com.gc.ioc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaochao
 * @create 2020-04-15 15:12
 */
@Component
@Slf4j
@Data
@AllArgsConstructor
public class Pig  {

  public static void main(String[] args) {

    Pig p = new Pig();
    p.setName("test1");
    Map<String,Pig> h = new HashMap<>();
    h.put("pig",p);
    System.out.println("h.get(\"pig\").getName() = " + h.get("pig").getName());

    p.setName("修改后");
    System.out.println("h.get(\"pig\").getName() = " + h.get("pig").getName());




  }

  private String name;

  public Pig(){
    log.info("Pig 构造方法...");
  }

  @PreDestroy
  public void destroy() throws Exception {
    log.info("Pig destroy @PreDestroy...");
  }

  @PostConstruct
  public void afterPropertiesSet() throws Exception {
    log.info("Pig init @PostConstruct...");
  }
}
