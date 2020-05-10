package com.example.code.common.dao;

import com.example.code.common.model.Column;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TableInfoDao {

    @Select("select table_name,column_name,data_type,is_nullable,character_maximum_length,column_key from information_schema.columns " +
            "where table_schema ='hmall' and table_name = #{table}")
    @Results(value = {
            @Result(column = "table_name", property = "tableName"),
            @Result(column = "column_name", property = "field"),
            @Result(column = "data_type", property = "dataType"),
            @Result(column = "is_nullable", property = "nullAble"),
            @Result(column = "character_maximum_length", property = "maxLen"),
            @Result(column = "column_key", property = "priKey")
    })
    List<Column> table(@Param("table") String table);
}
