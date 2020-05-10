package com.example.code.v2;

import com.example.code.common.config.Context;
import com.example.code.common.config.PropertyFactory;
import com.example.code.common.dao.TableInfoDao;
import com.example.code.common.model.Column;
import com.example.code.common.model.Table;
import com.example.code.util.CodeUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class TableHandler {

    private Table table;

    public TableHandler(String tableName) {
        table = new Table(tableName, wrapColumns(tableName));
    }

    private List<Column> wrapColumns(String tableName) {
        List<Column> columns = Context.getAc().getBean(TableInfoDao.class).table(tableName);
        if (!CollectionUtils.isEmpty(columns)) {
            for (Column c : columns) {
                Column column = new Column();
                column.setField(CodeUtil.fieldName(c.getField()));
                column.setDataType(PropertyFactory.get(c.getDataType()));
                c.setClassField(column);
            }
        }
        return columns;
    }

    public Table table() {
        return table;
    }
}
