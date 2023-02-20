package com.util;

import com.AceApplication;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 扫瞄项目里已经mapped的url
 *
 * @Classname: UrlMapperUtil
 * @Date: 2023/1/13 上午 09:48
 * @Author: kalam_au
 * @Description:
 */

public class UrlMapperUtil implements ApplicationContextAware {
    private static final Logger log = LogManager.getLogger(UrlMapperUtil.class.getName());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> controllerBeans = applicationContext.getBeansWithAnnotation(Controller.class);
       // Map<String, Object> restControllerBeans = applicationContext.getBeansWithAnnotation(RestController.class);

        List<String> list = new ArrayList<>();
        list.addAll(getUrl(controllerBeans, applicationContext));
       // list.addAll(getUrl(restControllerBeans, applicationContext));

    }

    private List<String> getUrl(Map<String, Object> beans, ApplicationContext applicationContext) {
        List<String> list = new ArrayList<>();
        for (String beanName : beans.keySet()) {
            Object value = applicationContext.getBean(beanName);
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(value.getClass(), RequestMapping.class);
            if (requestMapping == null) {
                continue;
            }
            String path = requestMapping.value()[0];
            log.info("-------------------------------------------");
            Method[] methods = value.getClass().getMethods();
            for (Method method : methods) {
                //每个方法必定含有下面的注解中的其中一个
                ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
                String url;
                String desc = "";
                if (apiOperation != null) {
                    desc = apiOperation.value();
                }
                RequestMapping mapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
                GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
                PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
                DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
                if (postMapping != null) {
                    url = path + postMapping.value()[0];
                } else if (getMapping != null) {
                    if (getMapping.value().length > 0) {
                        url = path + getMapping.value()[0];
                    } else {
                        log.warn("GetMapping.Value no definition !!!");
                        url = path;
                    }
                } else if (putMapping != null) {
                    url = path + putMapping.value()[0];
                } else if (deleteMapping != null) {
                    url = path + deleteMapping.value()[0];
                } else if (mapping != null) {  //mapping 顺序一定要在后面
                    if (mapping.value().length > 0) {
                        url = path + mapping.value()[0];
                    } else {
                        log.warn("RequestMapping.Get value no definition !!!");
                        url = path;
                    }
                } else {
                    continue;
                }
                //log.info("url : {}  , desc : {}, system_code:{}", url, desc, system_code);
                //log.info("Url: {}  , desc : {}", url, desc);
                log.info("Url: {}", url);
                list.add("Url: " + url);

                //url信息入库
            }
        }
        return list;
    }
}

