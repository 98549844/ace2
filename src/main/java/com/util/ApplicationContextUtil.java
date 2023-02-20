package com.util;

import com.AceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @Classname: ApplicationContextUtil
 * @Date: 2023/1/15 上午 12:15
 * @Author: kalam_au
 * @Description:
 */


public class ApplicationContextUtil {
    private static final Logger log = LogManager.getLogger(ApplicationContextUtil.class.getName());

    public ApplicationContext getApplicationContext() {
        log.info("get applicationContext from application main method");
        return AceApplication.applicationContext;
    }

    public ApplicationContext getApplicationContextFromContextLoader() {
        log.info("get applicationContext from ContextLoader.getCurrentWebApplicationContext");
        return ContextLoader.getCurrentWebApplicationContext();
    }


}

