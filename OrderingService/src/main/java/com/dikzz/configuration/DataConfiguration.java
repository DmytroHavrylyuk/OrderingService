package com.dikzz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by dikzz on 7/25/16.
 */
@Configuration
@ComponentScan(value = "com.dikzz.repository")
public class DataConfiguration {

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactory.setJpaProperties(getJpaProperties());
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.dikzz.domain");
        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource() {
        SingleConnectionDataSource singleConnectionDataSource = new SingleConnectionDataSource();
        singleConnectionDataSource.setAutoCommit(false);
        singleConnectionDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        singleConnectionDataSource.setUrl("jdbc:mysql://localhost:3306/OrderingService?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        singleConnectionDataSource.setUsername("root");
        singleConnectionDataSource.setPassword("dikzz");
        singleConnectionDataSource.setSuppressClose(true);
        return singleConnectionDataSource;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return vendorAdapter;
    }

}
