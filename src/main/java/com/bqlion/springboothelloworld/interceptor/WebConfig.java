package com.bqlion.springboothelloworld.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* *
 * Created by BqLion on 2019/8/6
 */
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionoInterceptor sessionoInterceptor;            //autowired 等于new了一个新对象

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionoInterceptor).addPathPatterns("/**");
    }
}
//每个controler都有一个映射地址  .addPathPatterns代表哪些需要经过本过滤器过滤 .excludePathPatterns代表哪些不需要经过过滤
