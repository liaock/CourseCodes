package com.jiketime.dbdemo.service;

import com.jiketime.dbdemo.bean.TOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by liaock on 2021/6/20
 **/
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    @DisplayName("insert 1 000 000 records into db")
    /**
     *  1.需要修改 配置文件中的max_allowed_packet.
     *  2.尝试调整各种批量插入的数据量，每次插入 100 000条比较快.
     *  3. 这个跟不同的机器性能也有关系.
     */
    public void insert() {
        List<TOrder> orderList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for(int k=0;k<10;k++) {
            for (int i = 0; i < 100000; i++) {
                TOrder order = TOrder.builder()
                        .id(UUID.randomUUID().toString().substring(0, 20))
                        .productId(UUID.randomUUID().toString().substring(0, 20))
                        .payMethod(true)
                        .build();
                orderList.add(order);
            }
            orderService.insert(orderList);
            orderList.clear();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("============共计耗时" + costTime/1000+ "秒===============");
    }
}