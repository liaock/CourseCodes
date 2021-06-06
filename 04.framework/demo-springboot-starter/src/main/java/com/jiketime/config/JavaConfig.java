package com.jiketime.config;

import com.jiketime.bean.Klass;
import com.jiketime.bean.School;
import com.jiketime.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaock on 2021/6/2
 **/
@Configuration
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(prefix = "school",name = "isopen",havingValue = "true")
public class JavaConfig {

    @Autowired
    SchoolProperties schoolProperties;

    @Bean(name = "student1")
    public Student getStudent1(){
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        return  student;
    }

    @Bean(name = "student2")
    public Student getStudent2(){
        Student student = new Student();
        student.setId(2);
        student.setName("李四");
        return  student;
    }

    @Bean(name = "student3")
    public Student getStudent3(){
        Student student = new Student();
        student.setId(3);
        student.setName("王五");
        return  student;
    }

    @Bean(name = "student100")
    public Student getStudent100(){
        Student student = new Student();
        student.setId(3);
        student.setName("王五");
        return  student;
    }

    @Bean
    public Klass klass(){
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(getStudent1());
        studentList.add(getStudent2());
        studentList.add(getStudent3());
        Klass klass = new Klass();
        klass.setStudents(studentList);
        return klass;
    }

    @Bean(name = "school")
    public School school(){
        School school = new School();
        school.setClass1(klass());
        school.setStudent100(getStudent100());
        school.setAddress(schoolProperties.getAddress());
        school.setTelNo(schoolProperties.getTelNo());
        return school;
    }
}
