package com.test.dao.impl;
/**
 * Created by liaock on 2021/6/4
 **/

import com.jiketime.bean.Student;
import com.test.annotation.CustomizedTransactional;
import com.test.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStudentDaoImpl implements StudentDao {

    @Autowired
    private ApplicationContext applicationContext;

    @CustomizedTransactional
    @Override
    public void insert(Student student) throws SQLException {
        // connection close后无法复用这里的 connection 为多例的bean.
        Connection connection = (Connection)applicationContext.getBean("connection");
        String sql = "insert into t_student values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.execute();
    }

    @Override
    public List<Student> selectList() throws SQLException {
        // connection close后无法复用这里的 connection 为多例的bean.
        Connection connection = (Connection)applicationContext.getBean("connection");
        String sql = "select * from t_student";
        List<Student> resultList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        // next() 类似指针 会往下移动,指定到对应的对象
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            Student student = new Student();
            student.setId(Integer.parseInt(id));
            student.setName(name);
            resultList.add(student);
        }
        return resultList;
    }

    @CustomizedTransactional
    @Override
    public void update(Student student) throws SQLException {
        // connection close后无法复用这里的 connection 为多例的bean.
        Connection connection = (Connection)applicationContext.getBean("connection");
        String sql = "update t_student set name = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setInt(2, student.getId());
        ps.executeUpdate();
    }

    @CustomizedTransactional
    @Override
    public void delete(int id) throws SQLException {

        // connection close后无法复用这里的 connection 为多例的bean.
        Connection connection = (Connection)applicationContext.getBean("connection");
        String sql = "delete from t_student where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
