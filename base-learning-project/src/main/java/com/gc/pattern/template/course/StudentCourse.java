package com.gc.pattern.template.course;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 学生课程
 * @author gaochao
 * @create 2020-09-27 14:22
 */
@Slf4j
@Data
public class StudentCourse extends NetworkCourse {

  private String name;

  public StudentCourse(String name,String courseName){
    super.setCourseName(courseName);
    this.name=name;
  }

  @Override
  protected void checkHomework() {
    log.info("学生{"+name+"}做完老师布置的{"+super.getCourseName()+"}作业后交给老师检查");
  }

  public static void main(String[] args) {
    NetworkCourse java = new StudentCourse("聂风","JAVA课程");
    java.setNeedCheck(true);
    java.createNetworkCourse();



    NetworkCourse bigData = new StudentCourse("步惊云","大数据课程");
    bigData.setNeedCheck(true);
    bigData.createNetworkCourse();
  }
}
