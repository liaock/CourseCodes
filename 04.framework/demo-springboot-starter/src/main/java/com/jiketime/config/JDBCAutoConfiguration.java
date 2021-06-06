package com.jiketime.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by liaock on 2021/6/4
 **/

@Configuration
@EnableConfigurationProperties(JDBCProperties.class)
@ConditionalOnProperty(prefix = "jdbc",name = "isopen",havingValue = "true")
public class JDBCAutoConfiguration {

    @Autowired
    private JDBCProperties jdbcProperties;

    @Bean
    @Scope(value = "prototype")
    public Connection connection(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcProperties.getJdbcUrl(), jdbcProperties.getUser(), jdbcProperties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
