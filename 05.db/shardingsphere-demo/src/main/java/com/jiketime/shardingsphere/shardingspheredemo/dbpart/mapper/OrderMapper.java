package com.jiketime.shardingsphere.shardingspheredemo.dbpart.mapper;

import com.jiketime.shardingsphere.shardingspheredemo.dbpart.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liaock on 2021/6/27
 **/
@Mapper
public interface OrderMapper {

    void insert(Order order);
}
