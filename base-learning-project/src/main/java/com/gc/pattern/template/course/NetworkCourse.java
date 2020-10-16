package com.gc.pattern.template.course;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 网课抽象模板
 * 会有固定的执行流程算法;并且有一个或者多个未实现的方法.
 * @author gaochao
 * @create 2020-09-27 11:45
 */
@Slf4j
@Data
public abstract class NetworkCourse {

  private String courseName;
  /** 钩子函数标识,用来控制流程 **/
  private boolean needCheck = Boolean.TRUE;

  protected final void createNetworkCourse(){
    //1.发布预习资料
    this.preResource();
    //2.在线授课
    this.liveTeach();
    //3.布置作业
    this.postWork();
    //4.检查作业,交给子类实现
    if (needCheck()){
      checkHomework();;
    }
  }

  private final void preResource(){
    log.info("老师提前发布"+courseName+"课程预习资料");
  }

  private final void liveTeach(){
    log.info("老师在线讲解"+courseName+"课程");
  }

  /**
   * 布置作业
   */
  private final void postWork(){
    log.info("老师"+courseName+"布置课堂作业");
  }

  /**
   * 检查作业
   */
  protected abstract void checkHomework();

  /**
   * 钩子函数:主要目的用来干预流程,使得流程控制更加灵活,更加符合业务实际.一般返回值是boolean或者int
   * @return
   */
  protected boolean needCheck(){
    return needCheck;
  }

}
