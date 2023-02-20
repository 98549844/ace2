package com.generator;

import com.google.common.base.CaseFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.StringUtils;

/**
 * @Classname: MapperNameGenerator
 * @Date: 2022/8/15 上午 09:37
 * @Author: kalam_au
 * @Description:
 */


public class MapperNameGenerator implements BeanNameGenerator {
    private static final Logger log = LogManager.getLogger(MapperNameGenerator.class.getName());

    private final String prefix;

    public MapperNameGenerator(String prefix) {
        this.prefix = prefix;
    }

    //主要用作local, remote datasource用, 未开发完
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String[] nameList = definition.getBeanClassName().split("\\.");
        String name = nameList[nameList.length - 1];

        if (StringUtils.hasLength(this.prefix)) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);
        } else {
            return this.prefix + name;
        }

    }
}

