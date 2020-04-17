package com.guoguo.warehouse.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginVerifyInterceptor loginVerifyInterceptor;
    @Autowired
    private DataPackInterceptor dataPackInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径
        List<String> list = new ArrayList<>();
        list.add("/warehouse/**");
        // 排除路径
        List<String> unlist = new ArrayList<>();
        unlist.add("/warehouse/user/login");
        unlist.add("/warehouse/user/ajax/login");
        unlist.add("/warehouse/goods/quantity/update/*");
        registry.addInterceptor(loginVerifyInterceptor).addPathPatterns(list).excludePathPatterns(unlist);
        registry.addInterceptor(dataPackInterceptor).addPathPatterns(list);
    }
}