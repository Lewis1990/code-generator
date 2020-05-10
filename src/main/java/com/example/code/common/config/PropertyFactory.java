package com.example.code.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {

    private static final Properties PROPERTIES = new Properties();

    static {

        try (InputStream is = PropertyFactory.class.getClassLoader().getResourceAsStream("type-mapper.properties")){
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
