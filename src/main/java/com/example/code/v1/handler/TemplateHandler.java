package com.example.code.v1.handler;

import com.example.code.common.model.JTemplate;
import freemarker.core.Macro;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class TemplateHandler {

    private final static String BASE_FORMAT = "%s.%s";

    private final static String FORMAT = "%s%s.%s";

    private Map macros;

    private JTemplate jTemplate;

    private TableWrapper tableWrapper;

    private TemplateHandler() {}

    public String file() {
        if ("yes".equals(jTemplate.getWithTable())) {
            return String.format(FORMAT,
                    tableWrapper.tableNameHump(),
                    jTemplate.getFileNameSuffix(),
                    jTemplate.getSuffix());
        }
        return String.format(BASE_FORMAT,
                jTemplate.getFileName(),
                jTemplate.getSuffix());
    }

    public String packageName() {
        return jTemplate.getPackageName();
    }

    public void tableWrapper(TableWrapper tableWrapper){
        this.tableWrapper = tableWrapper;
    }
    private void parseTemlate() {
        jTemplate = new JTemplate();
        jTemplate.setPackageName(value("_package_name"));
        jTemplate.setFileName(value("_file_name"));
        jTemplate.setFileNameSuffix(value("_file_name_suffix"));
        jTemplate.setSuffix(value("_suffix"));
        jTemplate.setWithTable(value("_with_table"));
    }

    private String value(String key){
        try {
            Macro macro = (Macro) macros.get(key);
            if (null != macro) {
                return String.valueOf(macro.getChildNodes().get(0));
            }
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static class Builder {

        private Map macros;

        public Builder withMacros(Map macros) {
            this.macros = macros;
            return this;
        }

        public TemplateHandler build() {
            TemplateHandler templateHandler = new TemplateHandler();
            templateHandler.macros = this.macros;
            templateHandler.parseTemlate();
            return templateHandler;
        }
    }

}
