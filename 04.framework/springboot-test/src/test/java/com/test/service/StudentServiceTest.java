package com.test.service;

import com.jiketime.bean.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by liaock on 2021/6/4
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void insert() throws SQLException {
        Student student = new Student();
        student.setId(1);
        student.setName("liaock");
        studentService.insert(student);

        student.setName("liudh");
        studentService.update(student);

        List<Student> students = studentService.selectList();
        Assert.assertEquals(students.isEmpty(), false);

        studentService.delete(1);
    }
}