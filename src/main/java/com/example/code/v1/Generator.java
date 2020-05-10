package com.example.code.v1;

import com.example.code.v1.handler.TableHandler;
import com.example.code.v1.handler.TableWrapper;
import com.example.code.v1.handler.TemplateHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.*;

public class Generator {

    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);

    private String outputPath = "D:\\projects\\TestTest\\src\\main\\java\\com\\example\\demo";

    private TableHandler tableHandler;

    private TemplateHandler templateHandler;

    public Generator() {
        initCfg();

        String tables = "h_admin_user,h_permission,h_role";
        List<String> list = Arrays.asList(tables.split(","));
        tableHandler = new TableHandler(new HashSet<>(list));
    }

    private void initCfg() {
        try {
            //设置模板文件所在的路径
            configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            //设置模板文件所使用的字符集，一般是utf-8
            configuration.setDefaultEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate() {
        templates().forEach(e -> {
            try {
                Template template = configuration.getTemplate(e);
                templateHandler = new TemplateHandler.Builder().withMacros(template.getMacros()).build();

                for (TableWrapper tableWrapper : tableHandler.list()) {
                    templateHandler.tableWrapper(tableWrapper);
                    template.process(tableWrapper.map(), writer());
                }
            } catch (IOException | TemplateException e1) {
                e1.printStackTrace();
            }
        });
    }

    private Writer writer() throws IOException {
        String FILE_FORMAT = "%s/%s";
        String path = String.format(FILE_FORMAT, path(templateHandler.packageName()), templateHandler.file());
        return new FileWriter(new File(path));
    }

    private List<String> templates() {
        URL resource = Generator.class.getClassLoader().getResource("templates");
        List<String> templates = new ArrayList<>();
        if (null != resource) {
            File file = new File(resource.getFile());
            Arrays.asList(Objects.requireNonNull(file.listFiles())).forEach(e -> templates.add(e.getName()));
        }
        return templates;
    }

    private String path(String path) {
        String pkgPath = outputPath + "/" +path;
        File f = new File(pkgPath);
        if (!f.exists()) {
            boolean res = f.mkdirs();
            if (!res) {
                throw new RuntimeException();
            }
        }
        return pkgPath;
    }
}
