package com.jiketime.dbdemo.mapper;

import com.jiketime.dbdemo.bean.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(TOrder record);

    void insertList(@Param("recordList") List<TOrder> recordList);

    TOrder selectByPrimaryKey(String id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);
}