package com.gc.pattern.observer.rate;

/**
 * test : 利用观察者模式设计一个程序，分析“人民币汇率”的升值或贬值对进口公司的进口产品成本或出口公司的出口产品收入以及公司的利润率的影响。
 * @author gaochao
 * @create 2020-06-01 17:39
 */

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇率:抽象目标
 */
abstract class Rate {

  protected List<Company> companyList = new ArrayList<>();

  public Rate addCompany(Company company){
    companyList.add(company);
    return this;
  }

  public void remove(Company company){
    companyList.remove(company);
  }

  abstract void notifyCompany(int number);

}

/**
 * 人民币汇率:具体目标
 */
class RMBRate extends Rate{

  @Override
  void notifyCompany(int number) {
    for (Object object : companyList){
      ((Company) object).response(number);
    }
  }

}

/**
 * 公司:抽象观察者
 */
interface Company{

  void response(int number);

}

/**
 * 进口公司:观察者
 * 当“人民币汇率”升值时，进口公司的进口产品成本降低且利润率提升
 * 当“人民币汇率”贬值时，进口公司的进口产品成本提升且利润率降低
 */
@Slf4j
class ImportCompany implements Company{

  @Override
  public void response(int number) {
    if (number > 1){
      log.info("人民币汇率升值["+number+"]个点,进口产品成本降低且利润率提升");
    }else {
      log.info("人民币汇率贬值["+number+"]个点,进口产品成本提升且利润率降低");
    }
  }
}

/**
 * 出口公司:观察者
 * 当“人民币汇率”升值时，出口公司的出口产品收入降低且利润率降低
 * 当“人民币汇率”贬值时，出口公司的出口产品收入提升且利润率提升
 */
@Slf4j
class OutputCompany implements Company{

  @Override
  public void response(int number) {
    if (number > 1){
      log.info("人民币汇率升值["+number+"]个点,出口产品收入降低且利润率降低");
    }else {
      log.info("人民币汇率贬值["+number+"]个点,出口产品收入提升且利润率提升");
    }
  }

}
