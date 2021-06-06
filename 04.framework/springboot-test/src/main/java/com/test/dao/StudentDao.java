package com.test.dao;

import com.jiketime.bean.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liaock on 2021/6/4
 **/
public interface StudentDao {

    void insert(Student student) throws SQLException;

    List<Student> selectList() throws SQLException;

    void update(Student student) throws SQLException;

    void delete(int id) throws SQLException;

}
