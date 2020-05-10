package com.example.code.common.model;

import com.example.code.util.CodeUtil;
import lombok.Getter;

import java.util.List;

@Getter
public class Table {

    private String tableName;

    private String className;

    private List<Column> columns;

    public Table(String tableName, List<Column> columns) {
        this.tableName = tableName;
        this.className = CodeUtil.className(tableName);
        this.columns = columns;
    }
}
