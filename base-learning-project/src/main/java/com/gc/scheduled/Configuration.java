package com.gc.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置类,进行线程池的配置
 * @author gaochao
 * @create 2020-09-27 14:37
 */
@Component
public class Configuration {

  /**
   *  线程池维护线程的最小数量.
   */
  int corePoolSize = 5;
  /**
   *  线程池维护线程的最大数量
   */
  int maxPoolSize = 10;
  /**
   *  队列最大长度
   */
  int queueCapacity = 10;
  /**
   *  线程池前缀
   */
  String threadNamePrefix = "gc-task-";

  @Bean(name = "GCExecutor")
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix(threadNamePrefix);
    //rejection-policy：当pool已经达到max size的时候，如何处理新任务
    //CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

}
