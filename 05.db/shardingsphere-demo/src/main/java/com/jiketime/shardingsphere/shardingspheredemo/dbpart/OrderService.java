package com.jiketime.shardingsphere.shardingspheredemo.dbpart;

import com.jiketime.shardingsphere.shardingspheredemo.dbpart.Order;
import com.jiketime.shardingsphere.shardingspheredemo.dbpart.OrderItem;
import com.jiketime.shardingsphere.shardingspheredemo.dbpart.mapper.OrderItemMapper;
import com.jiketime.shardingsphere.shardingspheredemo.dbpart.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaock on 2021/6/27
 **/

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    public void insertOrder(Order order, OrderItem orderItem){
        orderMapper.insert(order);
        // orderItemMapper.insert(orderItem);
    }

}
