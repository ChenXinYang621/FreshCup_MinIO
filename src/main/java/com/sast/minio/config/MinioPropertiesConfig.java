package com.sast.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-10-27 15:52
 **/
@Data
@Component
//绑定 yaml 配置文档的属性
@ConfigurationProperties(prefix = "minio")
public class MinioPropertiesConfig {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
