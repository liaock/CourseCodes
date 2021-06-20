package com.jiketime.dbdemo.service;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by liaock on 2021/6/20
 **/

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
         return DataSourceContextHolder.getDataSource();
    }
}
