package com.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.thymeleaf.dialect.SaTokenDialect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Classname: SaTokenConfig
 * @Date: 12/12/2021 10:31 AM
 * @Author: garlam
 * @Description:
 */

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(SaTokenConfig.class.getName());

   // @Override
    /* public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {
            try {
                System.out.println("-------- 前端访问path：" + SaHolder.getRequest().getRequestPath());
                StpUtil.checkLogin();
                System.out.println("-------- 此 path 校验成功：" + SaHolder.getRequest().getRequestPath());
            } catch (Exception e) {
                System.out.println("-------- 此 path 校验失败：" + SaHolder.getRequest().getRequestPath());
            }
        })).addPathPatterns("/**");
    }*/

    // 注册拦截器
    // @Override
//    public void addInterceptors11(InterceptorRegistry registry) {

//        // 注册Sa-Token的路由拦截器写法
//       // registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())) //after 1.31
//        registry.addInterceptor(new SaInterceptor(handler -> SaRouter.match("/**")
//                        //开放登陆,注册 url
//                        .notMatch("/ace/logging.html")
//                        .notMatch("/ace/login.html")
//                        .notMatch("/ace/registration.html")
//                        .notMatch("/ace/password/reset.html")
//                        .notMatch("/")
//                        //开放restController
//                        .notMatch("/rest/**")
//                        //开方api
//                        .notMatch("/api/**")
//                        .notMatch("/**/*.js",
//                                            "/**/*.png",
//                                            "/**/*.jpg",
//                                            "/favicon.ico",
//                                            "/**/*.css",
//                                            "/**/*.woff2",
//                                            "/**/*.woff",
//                                            "/**/*.ttf",
//                                            "/**/*.svg",
//                                            "/**/*.eot",
//                                            "/**/*.map",
//                                            "/images/**")
//                        //swagger
//                        .notMatch("/doc.html")
//                        .notMatch("/swagger-ui.html", "/csrf", "/webjars/**", "/swagger-resources/**", "/v2/**")
//                        .check(r -> StpUtil.checkLogin())
//                        )).addPathPatterns("/**"); //after 1.31
//
//    }


    //springboot
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        // registry.addInterceptor(new SaRouteInterceptor()) //1.30
        // registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())) //after 1.31
        registry.addInterceptor(new SaInterceptor(handler -> StpUtil.checkLogin())) //after 1.31
                //开放登陆,注册 url
                .excludePathPatterns(
                        "/ace/logging.html",
                        "/ace/login.html",
                        "/ace/registration.html",
                        "/ace/password/reset.html"
                        ,"/")
                //开放restController
                .excludePathPatterns( "/rest/**")
                //开方api
                .excludePathPatterns( "/api/**")
                .excludePathPatterns(
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.jpg",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.woff2",
                        "/**/*.woff",
                        "/**/*.ttf",
                        "/**/*.svg",
                        "/**/*.eot",
                        "/**/*.map",
                        "/images/**")
                //swagger
                .excludePathPatterns("/doc.html")
                .excludePathPatterns(
                        "/swagger-ui.html",
                        "/csrf",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/**")
                .addPathPatterns("/**");
    }

    // Sa-Token 标签方言 (Thymeleaf版)
    @Bean
    public SaTokenDialect getSaTokenDialect() {
        return new SaTokenDialect();
    }
}

