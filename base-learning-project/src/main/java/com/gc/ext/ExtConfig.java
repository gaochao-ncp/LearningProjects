package com.gc.ext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaochao
 * @create 2020-05-21 16:15
 */
@Configuration
public class ExtConfig {

  @Bean
  public Blue getBlue(){
    return new Blue();
  }

}
