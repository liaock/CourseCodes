package com.jiketime.dbdemo.config.properties;

import lombok.Data;

/**
 * Created by liaock on 2021/6/19
 **/
@Data
public class DataSourceProperties {

    protected String driver;

    protected String jdbcUrl;

    protected String username;

    protected String password;

    protected int connectionTimeout;

    protected int idleTimeout;

    protected int maximumPoolSize;

    protected int minimumIdle;
    
}
