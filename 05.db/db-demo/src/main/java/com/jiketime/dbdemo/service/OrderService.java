package com.jiketime.dbdemo.service;

import com.jiketime.dbdemo.annotation.DataSource;
import com.jiketime.dbdemo.bean.TOrder;
import com.jiketime.dbdemo.mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liaock on 2021/6/20
 **/

@Service
public class OrderService {

    @Autowired
    TOrderMapper tOrderMapper;

    @Transactional
    @DataSource(name = "financial-slave")
    public void insert(List<TOrder> orders){

        tOrderMapper.insertList(orders);

    }

    public void insert(TOrder order){

        tOrderMapper.insert(order);

    }

}
