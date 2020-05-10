package com.example.code.v2;

import com.example.code.common.model.FieldMapper;
import com.example.code.common.model.JTemplate;
import freemarker.core.Macro;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModelException;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TemplateHandler {

    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);

    private List<JTemplate> templates;

    static {
        try {
            //设置模板文件所在的路径
            configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates/v2"));
            //设置模板文件所使用的字符集，一般是utf-8
            configuration.setDefaultEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TemplateHandler() {
        initTemplate();
    }

    private void initTemplate() {
        templates = new ArrayList<>();
        URL resource = Generator.class.getClassLoader().getResource("templates");
        if (null != resource) {
            File[] files = new File(resource.getFile()+"/v2").listFiles();
            if (null != files) {
                for (File file : files) {
                    try {
                        doInitTemplate(configuration.getTemplate(file.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void doInitTemplate(Template template) throws TemplateModelException {
        JTemplate jTemplate = new JTemplate();
        Field[] fields = JTemplate.class.getDeclaredFields();
        for (Field field : fields) {
            FieldMapper mapper = field.getAnnotation(FieldMapper.class);
            if (null != mapper) {
                Macro macro = (Macro) template.getMacros().get(mapper.value());
                if (null != macro) {
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, jTemplate, String.valueOf(macro.getChildNodes().get(0)));
                }
            }
        }
        jTemplate.setTemplate(template);
        templates.add(jTemplate);
    }

    public List<JTemplate> templates() {
        return this.templates;
    }
}
