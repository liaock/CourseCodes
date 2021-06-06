package com.test.dao.impl;

import com.jiketime.bean.Student;
import com.test.annotation.CustomizedTransactional;
import com.test.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaock on 2021/6/5
 *
 * 通过自定义starter 引入hikari 连接池.（hikariConnection持有）
 **/
@Service
public class HikariStudentDaoImpl implements StudentDao {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("hikariDataSource")
    private DataSource hikariDataSource;

    @CustomizedTransactional
    @Override
    public void insert(Student student) throws SQLException {
        Connection connection = hikariDataSource.getConnection();
        String sql = "insert into t_student values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.execute();
    }

    @Override
    public List<Student> selectList() throws SQLException {
        Connection connection = hikariDataSource.getConnection();
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
        Connection connection = hikariDataSource.getConnection();
        String sql = "update t_student set name = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setInt(2, student.getId());
        ps.executeUpdate();
    }

    @CustomizedTransactional
    @Override
    public void delete(int id) throws SQLException {
        Connection connection = hikariDataSource.getConnection();
        String sql = "delete from t_student where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

}
