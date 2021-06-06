package com.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * 自定义事务切面
 * Created by liaock on 2021/6/5
 **/
@Aspect
@Component
@Slf4j
public class CustomizedTransactionalAspect {

    @Autowired
    ApplicationContext applicationContext;


    @Pointcut("@annotation(com.test.annotation.CustomizedTransactional)")
    private void cutMethod(){

    }

    @Around("cutMethod()")
    public void around(ProceedingJoinPoint joinPoint) throws SQLException {
        // connection close后无法复用这里的 connection 为多例的bean.
        Connection connection = (Connection)applicationContext.getBean("connection");
        String name = joinPoint.getSignature().getName();
        log.info("--------------- 进入方法" + name + "开启事务--------------------");
        connection.setAutoCommit(false);
        try {
            joinPoint.proceed();
            log.info("--------------- 方法" + name + "成功执行，提交事务--------------------");
            connection.commit();
        } catch (Throwable throwable) {
            log.error("执行失败，回滚事务",throwable);
            log.info("--------------- 方法" + name + "执行失败，回滚事务--------------------");
            connection.rollback();
        }finally {
            connection.close();
        }
    }




}
