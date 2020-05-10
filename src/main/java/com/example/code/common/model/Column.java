package com.example.code.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {

    private String tableName;

    private String field;

    private String dataType;

    private String nullAble;

    private long maxLen;

    private String priKey;

    private Column classField;
}
