package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname: aceConfig
 * @Date: 4/5/2021 10:53 下午
 * @Author: garlam
 * @Description:
 */

@Component
@PropertySource(value = {"classpath:ace.properties"}, encoding = "UTF-8", name = "ace.properties")
@PropertySource(value = {"classpath:swagger2.properties"}, encoding = "UTF-8", name = "swagger2.properties")
@PropertySource(value = {"classpath:application.yml"}, encoding = "UTF-8", name = "application.yml")
public class AceConfig {
    private static final Logger log = LogManager.getLogger(AceConfig.class.getName());
    @Value("${ace.name}")
    private String name;
    @Value("${ace.version}")
    private String version;
    @Value("${swagger.enabled}")
    private boolean swaggerEnable;
    @Value("${docHtml.enabled}")
    private boolean docHtmlEnabled;
    @Value("${ace.indexEnable}")
    private boolean indexEnable;
    @Value("${spring.profiles.active}")
    private String profile;

    public static final String DEV="dev";
    public static final String UAT="uat";
    public static final String PROD="prod";
    public static final String DOCKER="docker";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isSwaggerEnable() {
        return swaggerEnable;
    }

    public void setSwaggerEnable(boolean swaggerEnable) {
        this.swaggerEnable = swaggerEnable;
    }

    public boolean isDocHtmlEnabled() {
        return docHtmlEnabled;
    }

    public void setDocHtmlEnabled(boolean docHtmlEnabled) {
        this.docHtmlEnabled = docHtmlEnabled;
    }

    public boolean isIndexEnable() {
        return indexEnable;
    }

    public void setIndexEnable(boolean indexEnable) {
        this.indexEnable = indexEnable;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}

