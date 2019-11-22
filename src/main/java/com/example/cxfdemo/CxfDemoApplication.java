package com.example.cxfdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CxfDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CxfDemoApplication.class, args);
        System.out.println("=========项目启动成功========");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CxfDemoApplication.class);
    }
}
