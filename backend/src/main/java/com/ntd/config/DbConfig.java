package com.ntd.config;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
//@PropertySource(value = "classpath : application.properties")
@EntityScan(basePackages = {"com.ntd.*"})
@EnableJpaRepositories(basePackages = {"com.ntd.*"})
public class DbConfig {


    @Value("${database.driver}")
    String databaseDriver;

    @Value("${database.url}")
    String databaseUrl;

    @Value("${database.id}")
    String databaseId;

    @Value("${database.pw}")
    String databasePw;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseId);
        dataSource.setPassword(databasePw);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.ntd.*" });

//        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
//        properties.put("hibernate.physical_naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

//        #spring.jpa.properties.hibernate.show_sql=true
//#spring.jpa.properties.hibernate.format_sql=true
//#spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        /*properties.setProperty("physical_naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("spring.jpa.properties.hibernate.show_sql","true");
        properties.setProperty("spring.jpa.properties.hibernate.format_sql","true");
        properties.setProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation","true");*/

        return properties;
    }
}
