package com.dikzz.configuration;

import com.dikzz.service.filter.MyFilter;
import org.springframework.context.ApplicationEventPublisher;
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
    public ZooKeeperRegistrar zooKeeperRegistrar(ApplicationEventPublisher publisher) {
        return new ZooKeeperRegistrar();
    }
    @Bean
    public MyFilter filter() {
        return new MyFilter();
    }

}
