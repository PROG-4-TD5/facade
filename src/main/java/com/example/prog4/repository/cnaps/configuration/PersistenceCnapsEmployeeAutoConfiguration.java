package com.example.prog4.repository.cnaps.configuration;

import java.util.HashMap;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import static com.example.prog4.repository.configuration.LocalContainerEntityManagerFactoryBeanCreator.createEntityManagerFactoryBean;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "com.example.prog4.repository.cnaps", entityManagerFactoryRef = "cnapsEmployeeEntityManager", transactionManagerRef = "cnapsEmployeeTransactionManager")
@AllArgsConstructor
public class PersistenceCnapsEmployeeAutoConfiguration {
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean cnapsEmployeeEntityManager() {
    final HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.physical_naming_strategy",
        CamelCaseToUnderscoresNamingStrategy.class.getName());
    return createEntityManagerFactoryBean(cnapsEmployeeDataSource(),
        "com.example.prog4.repository.cnaps",
        properties);

  }

  @Bean(name = "cnapsEmployeeDataSource")
  @ConfigurationProperties(prefix = "spring.second-datasource")
  public DataSource cnapsEmployeeDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public PlatformTransactionManager cnapsEmployeeTransactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(cnapsEmployeeEntityManager().getObject());
    return transactionManager;
  }

}