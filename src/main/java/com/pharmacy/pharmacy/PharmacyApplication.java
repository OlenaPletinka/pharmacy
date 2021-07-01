package com.pharmacy.pharmacy;


import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class PharmacyApplication {

  public static void main(String[] args) throws LiquibaseException {
    ConfigurableApplicationContext context = SpringApplication.run(PharmacyApplication.class, args);

    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(context.getBean(DataSource.class));
    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
    liquibase.setShouldRun(true);
    liquibase.afterPropertiesSet();

  }
}
