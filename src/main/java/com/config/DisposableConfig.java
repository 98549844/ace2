package com.config;

import cn.dev33.satoken.stp.StpUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Classname: DisposableConfig
 * @Date: 13/12/2021 4:30 AM
 * @Author: garlam
 * @Description:
 */

@Component
public class DisposableConfig implements DisposableBean {
    private static final Logger log = LogManager.getLogger(DisposableConfig.class.getName());

    private HttpServletRequest request;

    private HttpServletRequest getRequest() {
        if (NullUtil.isNotNull(RequestContextHolder.getRequestAttributes())) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            request = servletRequestAttributes.getRequest();
            return request;
        } else {
            return null;
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("Shutting down AceApplication ... ");
        if (NullUtil.isNotNull(getRequest())) {
            // 注销此Session会话 (从持久库删除此Session)
            StpUtil.getSession().logout();
            StpUtil.logout();
        }
        log.info("AceApplication shutdown completed");
    }
}

