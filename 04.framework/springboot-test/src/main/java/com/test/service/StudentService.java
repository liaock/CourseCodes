package com.test.service;

import com.jiketime.bean.Student;
import com.test.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by liaock on 2021/6/4
 **/

@Service
public class StudentService {

    @Autowired
    @Qualifier("hikariStudentDaoImpl")   // 通过hikari连接池
    //@Qualifier("jdbcStudentDaoImpl")   // 不通过连接池
    StudentDao studentDao;

    public void insert(Student student) throws SQLException {
        studentDao.insert(student);
    }

    public List<Student> selectList() throws SQLException {
        List<Student> students = studentDao.selectList();
        return students;
    }

    public void update(Student student) throws SQLException {
        studentDao.update(student);
    }

    public void delete(int id) throws SQLException {
        studentDao.delete(id);
    }


}
