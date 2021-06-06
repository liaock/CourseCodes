package com.jiketime.bean;

import lombok.Data;

@Data
public class School {

    Klass class1;

    Student student100;

    /**
     * 地址
     */
    String address;

    /**
     * 联系电话
     */
    String telNo;



    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
