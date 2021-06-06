package com.test.client;

import com.jiketime.bean.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liaock on 2021/6/3
 **/

@Service
public class TestService {

    @Autowired
    private School school;

    public void ding(){
        school.ding();
    }

}
