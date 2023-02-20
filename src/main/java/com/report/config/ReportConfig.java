package com.report.config;

import com.constant.AceEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @Classname: ReportConfig
 * @Date: 18/7/2021 2:29 上午
 * @Author: garlam
 * @Description:
 */


@Component
public class ReportConfig {
    private static final Logger log = LogManager.getLogger(ReportConfig.class.getName());
    private String forName;
    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        this.url = AceEnvironment.environment.getProperty("spring.datasource.url");
        return url;
    }

    public String getUserName() {
        this.userName = AceEnvironment.environment.getProperty("spring.datasource.username");
        return userName;
    }

    public String getPassword() {
        this.password = AceEnvironment.environment.getProperty("spring.datasource.password");
        return password;
    }

    public String getForName() {
        this.forName = AceEnvironment.environment.getProperty("spring.datasource.driver-class-name");
        return forName;
    }


}

