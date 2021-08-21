package com.MIBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.MIBlog.dao")
public class MIBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MIBlogApplication.class,args);
    }
}
