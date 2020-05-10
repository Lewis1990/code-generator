
package com.example.code;

import com.example.code.config.BeanConfig;
import com.example.code.dao.TableInfoDao;
import com.example.code.model.TableInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CodeApplication {

    static final String BASE_PACKAGE_PATH = "output/";

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);
        TableInfoDao dao = ac.getBean(TableInfoDao.class);

        List<TableInfo> list = dao.table("h_admin_user");
        list.forEach(e -> {
            TableInfoManager manager = new TableInfoManager(e);
            manager.generate();
        });
    }


}
