package com.example.code.v1.handler;

import com.example.code.common.config.Context;
import com.example.code.common.dao.TableInfoDao;
import com.example.code.common.model.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableHandler {

    private Set<String> tables;

    private List<TableWrapper> list = new ArrayList<>();

    public TableHandler(Set<String> tables) {
        this.tables = tables;
        this.init();
    }

    private void init() {
        TableInfoDao tableInfoDao = Context.getAc().getBean(TableInfoDao.class);
        tables.forEach(e -> {
            List<Column> table = tableInfoDao.table(e);
            TableWrapper tableWrapper = new TableWrapper(e, table);
            list.add(tableWrapper);
        });
    }

    public List<TableWrapper> list() {
        return list;
    }

}
