package com.example.code.v1.handler;

import com.example.code.util.CodeUtil;
import com.example.code.common.config.PropertyFactory;
import com.example.code.common.model.Column;
import com.example.code.v1.service.TableService;
import com.example.code.v1.service.TableServiceImpl;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWrapper {

    private String tableName;

    private String className;

    private List<Column> tableInfo;

    TableWrapper(String tableName, List<Column> tableInfo) {
        this.tableName = tableName;
        this.tableInfo = tableInfo;
        this.className = className();
    }

    public int size() {
        return CollectionUtils.isEmpty(tableInfo) ? 0 : tableInfo.size();
    }

    public String tableNameHump() {
        return this.className;
    }

    public TableService tableInfo() {
        return new TableServiceImpl(tableInfo);
    }

    private String className() {
        return CodeUtil.className(tableName);
    }

    public Map<String, Object> map() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tableName" , this.tableName);
        map.put("className" , this.className);
        tableInfo.forEach(e -> {
            Column column = new Column();
            column.setField(CodeUtil.fieldName(e.getField()));
            column.setDataType(PropertyFactory.get(e.getDataType()));
            e.setClassField(column);
        });
        map.put("field", tableInfo);
        return map;
    }
}
