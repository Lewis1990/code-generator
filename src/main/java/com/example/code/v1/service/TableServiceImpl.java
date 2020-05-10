package com.example.code.v1.service;

import com.example.code.common.model.Column;

import java.util.List;

public class TableServiceImpl implements TableService {

    private List<Column> tableInfo;

    public TableServiceImpl(List<Column> tableInfo) {
        this.tableInfo = tableInfo;
    }


}
