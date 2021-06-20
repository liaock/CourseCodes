package com.jiketime.dbdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liaock on 2021/6/19
 **/

@Data
@ConfigurationProperties(prefix = "db1.datasource")
public class DataSource1Properties extends DataSourceProperties{


}
