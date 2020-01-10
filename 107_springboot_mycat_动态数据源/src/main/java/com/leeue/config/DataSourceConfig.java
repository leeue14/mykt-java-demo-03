package com.leeue.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * 创建可读数据源
     *
     * @return
     */
    @Bean(name = "selectDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.select") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {

        return DataSourceBuilder.create().build();
    }

    /**
     * 创建可写数据源
     *
     * @return
     */
    @Bean(name = "updateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.update") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {

        return DataSourceBuilder.create().build();
    }

}
