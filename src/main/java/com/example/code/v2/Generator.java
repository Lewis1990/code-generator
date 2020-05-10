package com.example.code.v2;

import com.example.code.common.constant.Constants;
import com.example.code.common.model.JTemplate;
import com.example.code.common.model.Table;
import com.example.code.util.CodeUtil;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {

    //todo 放到配置文件
    private String outputPath = "D:\\projects\\code\\output";

    private List<String> tables;

    private TemplateHandler templateHandler = new TemplateHandler();

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    //以模板为基准
    public void generate() {
        List<JTemplate> templates = templateHandler.templates();
        Map<String, Table> map = map();
        templates.forEach(e -> {
            map.forEach((k, v) -> {
                try (Writer writer = writer(e.getPackageName(), fileName(e, v))){
                    e.getTemplate().process(v, writer);
                } catch (IOException | TemplateException e1) {
                    e1.printStackTrace();
                }
            });
        });
    }

    private Map<String, Table> map() {
        Map<String, Table> map = new HashMap<>();
        tables.forEach(e -> {
            TableHandler handler = new TableHandler(e);
            map.put(handler.table().getClassName(), handler.table());
        });
        return map;
    }

    private String fileName(JTemplate template, Table table) {
        if (template.isWithTable()) {
            return String.format("%s%s.%s",
                    table.getClassName(),
                    StringUtils.isBlank(template.getFileNameSuffix()) ? "" : template.getFileNameSuffix(),
                    template.getSuffix());
        }
        return String.format("%s.%s",
                template.getFileName(),
                template.getSuffix());
    }

    private Writer writer(String path, String file) throws IOException {
        String pkgPath = outputPath + "/" + path;
        CodeUtil.buildPath(pkgPath);
        return new FileWriter(new File(String.format(Constants.FILE_FORMAT, pkgPath, file)));
    }
}
