package com.guoguo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径
        List<String> list = new ArrayList<>();
        list.add("/admin/**");
        // 非拦截路径
        List<String> excludePathList=new ArrayList<>();
        excludePathList.add("/admin/user/ajax/login");
        excludePathList.add("/admin/user/ajax/check/name");
        excludePathList.add("/admin/user/logout");
        excludePathList.add("/admin/doc/look/*");
        registry.addInterceptor(loginInterceptor).excludePathPatterns(excludePathList).addPathPatterns(list);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射图片保存地址
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + fengyulouProperty.getFilePath() + "/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("admin-user-login");
        registry.addViewController("/turn").setViewName("common/turn");
    }
}