package com.example.code.common.model;

import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JTemplate {

    private String templateName;

    @FieldMapper("_package_name")
    private String packageName;

    @FieldMapper("_file_name")
    private String fileName;

    @FieldMapper("_file_name_suffix")
    private String fileNameSuffix;

    @FieldMapper("_suffix")
    private String suffix;

    @FieldMapper("_with_table")
    private String withTable;

    private Template template;

    public boolean isWithTable() {
        return "yes".equals(withTable);
    }
}
