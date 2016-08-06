package com.dikzz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by dg on 7/25/16.
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:ordering.properties")
public class ApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ZooKeeperRegistrar zooKeeperRegistrar() {
        return new ZooKeeperRegistrar();
    }

}
