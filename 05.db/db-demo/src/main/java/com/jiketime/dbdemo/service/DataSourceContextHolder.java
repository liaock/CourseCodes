package com.jiketime.dbdemo.service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by liaock on 2021/6/20
 **/

@Slf4j
public class DataSourceContextHolder {

    private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    public static void switchDataSource(String datasource) {
        log.debug("switchDataSource: {}", datasource);
        datasourceContext.set(datasource);
    }

    public static String getDataSource() {
        return datasourceContext.get();
    }

    public static void clear() {
        datasourceContext.remove();
    }

}
