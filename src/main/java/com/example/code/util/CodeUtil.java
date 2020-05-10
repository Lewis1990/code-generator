package com.example.code.util;

import java.io.File;

public class CodeUtil {

    public static String upper(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    public static String removePrefix(String value, String prefix) {
        return value.replace("h_", "");
    }

    public static String className(String value) {
        StringBuilder buff = new StringBuilder();
        String[] str = removePrefix(value, "").split("_", -1);
        for (String s : str) {
            buff.append(CodeUtil.upper(s));
        }
        return buff.toString();
    }

    public static String fieldName(String value){
        StringBuilder buff = new StringBuilder();
        String[] str = removePrefix(value, "").split("_", -1);
        for (int i = 0 ; i < str.length ; i++) {
            String val = str[i];
            if (i != 0) {
                val = upper(val);
            }
            buff.append(val);
        }
        return buff.toString();
    }

    public static void buildPath(String path) {
        File f = new File(path);
        if (!f.exists()) {
            boolean res = f.mkdirs();
            if (!res) {
                throw new RuntimeException();
            }
        }
    }

}
