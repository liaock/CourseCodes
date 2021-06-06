package com.jiketime.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liaock on 2021/6/4
 **/

@ConfigurationProperties(prefix = "jdbc")
@Data
@Setter
@Getter
public class JDBCProperties {

    private String drvier;

    private String jdbcUrl;

    private String user;

    private String password;

}
