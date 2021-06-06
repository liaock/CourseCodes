package com.jiketime.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liaock on 2021/6/5
 **/

@ConfigurationProperties(prefix = "hikari")
@Data
@Setter
@Getter
public class HikariProperties {

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

    private long connectionTimeout;

    private long idleTimeout;

    private int maximumPoolSize;

    private int minimumIdle;

}
