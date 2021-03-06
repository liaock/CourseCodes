package com.jiketime.dbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.jiketime.dbdemo.mapper")
public class DbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbDemoApplication.class, args);
    }

}
