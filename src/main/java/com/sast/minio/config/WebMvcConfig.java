package com.sast.minio.config;

import com.sast.minio.until.CustomJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @program: minio
 * @author: cxy621
 * @create: 2021-11-03 21:25
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private CustomJsonHttpMessageConverter converter;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
