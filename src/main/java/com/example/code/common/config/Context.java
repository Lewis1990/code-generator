package com.example.code.common.config;

import org.springframework.context.ApplicationContext;

public class Context {

    private static ThreadLocal<ApplicationContext> tl = new ThreadLocal<>();

    public static ApplicationContext getAc() {
        return tl.get();
    }

    public static void setAc(ApplicationContext ac) {
        tl.set(ac);
    }
}
