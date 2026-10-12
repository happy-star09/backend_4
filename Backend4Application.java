package com.example.backend_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 启动类
@SpringBootApplication
public class Backend4Application {

    public static void main(String[] args) {
        SpringApplication.run(Backend4Application.class, args); // 启动IoC容器和Tomcat服务器，把当前类作为主配置类传入
    }

}
