package com.sast.minio.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 16:13
 **/
@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinioConfig {
    @Resource
    private MinioPropertiesConfig minioPropertiesConfig;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioPropertiesConfig.getEndpoint())
                .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
                .build();
    }
}
