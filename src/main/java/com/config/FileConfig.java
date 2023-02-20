package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @Classname: FileConfig
 * @Date: 12/7/2021 6:47 下午
 * @Author: garlam
 * @Description:
 */

@Component
public class FileConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(FileConfig.class.getName());

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件大小
        factory.setMaxFileSize(DataSize.parse("10240MB"));
        // 上传的总文件大小
        factory.setMaxRequestSize(DataSize.parse("20480MB"));
        return factory.createMultipartConfig();
    }


}

