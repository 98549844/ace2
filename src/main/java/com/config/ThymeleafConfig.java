package com.config;

import com.interceptor.Interceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @Classname: ThymeleafConfig
 * @Date: 1/7/2021 2:27 上午
 * @Author: garlam
 * @Description:
 */

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(ThymeleafConfig.class.getName());

    /**
     * 修改主页
     *
     * @param registry registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //set static/login.html为主页
        //registry.addViewController("/").setViewName("forward:/ace.html");
        // registry.addViewController("/").setViewName("ace.html");

        //registry.addViewController("/").setViewName("/templates/ace/login.html");

        //call controller url
        //registry.addViewController("/").setViewName("ace/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    //开启拼截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/js/**", "/css/**", "/images/**");
        //addPathPatterns("/**")对所有请求都拦截，但是排除了/toLogin和/login请求的拦截
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new Interceptor());
        interceptorRegistration.excludePathPatterns("/images/**");
        interceptorRegistration.excludePathPatterns("/css/**");
        interceptorRegistration.excludePathPatterns("/js/**");
        interceptorRegistration.excludePathPatterns("/fonts/**");
        interceptorRegistration.excludePathPatterns("/font-awesome/**");
        interceptorRegistration.excludePathPatterns("/favicon.ico");
        //开方swagger访问
        interceptorRegistration.excludePathPatterns("/swagger-ui.html/**");
        interceptorRegistration.excludePathPatterns("/swagger-resources/**");
        interceptorRegistration.addPathPatterns("/**");
    }


    /**
     * 设置访问静态文件路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
        //  registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        //  registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        // 其他静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // registry.addResourceHandler("/files/**").addResourceLocations("classpath:/static/files/");
        // swagger增加url映射
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * config access resource folder
     *
     * @return resolver
     */

    //@Bean(name = "自定义beanName")
    //@Bean("自定义beanName")
    @Bean
    public ClassLoaderTemplateResolver resolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //access to resources
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(0);
        resolver.setCheckExistence(true);
        return resolver;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        return passwordEncoder;
    }
}

