package com.jiketime.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by liaock on 2021/6/5
 **/

@Configuration
@EnableConfigurationProperties(HikariProperties.class)
@ConditionalOnProperty(prefix = "hikari",name = "isopen",havingValue = "true")
public class HikariDataSourceAutoConfiguration {

    @Autowired
    HikariProperties hikariProperties;

    @Bean("hikariDataSource")
    @ConditionalOnMissingBean(name = "dataSource")
    public DataSource getDateSource(){
        HikariConfig config = new HikariConfig();
        config.setAutoCommit(true);
        config.setConnectionTimeout(hikariProperties.getConnectionTimeout());
        config.setDriverClassName(hikariProperties.getDriverClassName());
        config.setJdbcUrl(hikariProperties.getJdbcUrl());
        config.setUsername(hikariProperties.getUsername());
        config.setPassword(hikariProperties.getPassword());
        config.setMaximumPoolSize(hikariProperties.getMaximumPoolSize());
        config.setMinimumIdle(hikariProperties.getMinimumIdle());
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean("hikariConnection")
    public Connection connection(){
        DataSource dateSource = getDateSource();
        Connection connection = null;
        try {
            connection = dateSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
