package com.jiketime.dbdemo.config;

import com.jiketime.dbdemo.config.properties.DataSource1Properties;
import com.jiketime.dbdemo.config.properties.DataSource2Properties;
import com.jiketime.dbdemo.config.properties.DataSourceProperties;
import com.jiketime.dbdemo.service.RoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaock on 2021/6/19
 **/

@Configuration
@EnableConfigurationProperties(value = {DataSource1Properties.class, DataSource2Properties.class})
public class DataSourceConfig {


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource financialMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource financialSlaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "routingDataSource")
    public RoutingDataSource routingDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("financial-master", financialMasterDataSource());
        dataSourceMap.put("financial-slave", financialSlaveDataSource());

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(financialMasterDataSource());
        return routingDataSource;
    }



//    @Bean("dataSource1")
//    public DataSource getDataSource1(DataSource1Properties dataSource1Properties){
//        return createDataSource(dataSource1Properties);
//    }


    private DataSource createDataSource(DataSourceProperties dataSourceProperties){
        HikariConfig config = new HikariConfig();
        config.setAutoCommit(true);
        config.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());
        config.setDriverClassName(dataSourceProperties.getDriver());
        config.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        config.setUsername(dataSourceProperties.getUsername());
        config.setPassword(dataSourceProperties.getPassword());
        config.setMaximumPoolSize(dataSourceProperties.getMaximumPoolSize());
        config.setMinimumIdle(dataSourceProperties.getMinimumIdle());
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }



}
