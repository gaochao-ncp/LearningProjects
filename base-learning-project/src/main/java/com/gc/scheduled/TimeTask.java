package com.gc.scheduled;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author gaochao
 * @create 2020-09-16 18:13
 */
@EnableScheduling
@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})//DruidDataSourceAutoConfigure.class
@Slf4j
public class TimeTask {

  public static void main(String[] args) {
    SpringApplication.run(TimeTask.class,args);
  }

  @Async("GCExecutor")
  @Scheduled(cron = "0/1 * * * * ?")
  public void test(){
    System.out.println(Thread.currentThread().getName()+"::{"+ DateUtil.now()+"}::{excutor method()}");
  }

}
