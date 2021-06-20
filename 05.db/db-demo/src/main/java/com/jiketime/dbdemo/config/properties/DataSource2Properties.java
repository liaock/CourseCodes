package com.jiketime.dbdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liaock on 2021/6/19
 **/
@Data
@ConfigurationProperties(prefix = "db2.datasource")
public class DataSource2Properties extends DataSourceProperties{


}
