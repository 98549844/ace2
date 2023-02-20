package com.interceptor;

import com.controller.LoginController;
import com.util.BeanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.common.CommonController;

import java.io.IOException;

/**
 * @Classname: Interceptor
 * @Date: 28/6/2021 9:46 下午
 * @Author: garlam
 * @Description:
 */


public class Interceptor extends CommonController implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(Interceptor.class.getName());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        String status = Integer.toString(response.getStatus());
        String RequestURI = request.getRequestURI();
        log.info("status: {}; request uri: {}", status, RequestURI);

        //boolean isLogin = isLogin();
        /*
        String root = "/";
        String index = "/ace/login.html";
        String logging = "/ace/logging.html";
        if (!isLogin && !index.equals(RequestURI) && !root.equals(RequestURI) && !logging.equals(RequestURI)) {
            log.info("isLogin: " + false);
            log.info("preHandler allow [/]; [/ace/login.html]; [/ace/logging.html]");
            response.sendRedirect("/ace/login.html");
            return false;
        }
        */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //  log.info("response.status: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //preHandle返回true后, afterCompletion方法会执行

    }


}

