package com.jiketime.shardingsphere.shardingspheredemo.dbpart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Created by liaock on 2021/6/27
 **/
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void insertOrder() {
        for (int i = 0; i < 4; i++) {
            Order order = Order.builder()
                    .orderNo("A000" + i)
                    .createName("订单 " + i)
                    .price(new BigDecimal("" + i))
                    .build();

            OrderItem orderItem = OrderItem.builder()
                    .itemId(order.getOrderId())
                    .orderNo("A000" + i)
                    .itemName("服务项目" + i)
                    .price(new BigDecimal("" + i))
                    .build();
            orderService.insertOrder(order, orderItem);

        }
    }
}