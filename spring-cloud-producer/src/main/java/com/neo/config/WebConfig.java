package com.neo.config;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-16 18:07
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{
    @Autowired
    JsonObjectArgResolverHandler jsonObjectArgResolverHandler;
    @Override
//    参数解析器，被框架回调
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(jsonObjectArgResolverHandler);
    }

}