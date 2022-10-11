package com.bitcamp.board.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan(value="com.bitcamp.board")
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig() 생성자 호출됨!!");
  }


  @Bean("transactionManager")
  public PlatformTransactionManager createTransactionManager(DataSource ds) {
    System.out.println("TransactionManager() 호출됨!");
    return new DataSourceTransactionManager(ds);
  }


  @Bean("dataSource")
  public DataSource createDataSource() {
    System.out.println("createDataSource() 호출됨!");
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setUrl("jdbc:mariadb://localhost:3306/studydb");
    ds.setUsername("study");
    ds.setPassword("1111");
    return ds;
  }

}
