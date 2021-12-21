package com.sast.minio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.sast.minio.mapper")
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class MinioApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }

}
