package com.example.code;

import com.example.code.common.config.BeanConfig;
import com.example.code.common.config.Context;
import com.example.code.v2.Generator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MyMain {

    public static void main(String[] args) {
        Context.setAc(new AnnotationConfigApplicationContext(BeanConfig.class));

        String tables = "h_admin_user,h_permission,h_role";
        List<String> list = Arrays.asList(tables.split(","));
        Generator generator = new Generator();
        generator.setTables(list);
        generator.generate();
    }
}
