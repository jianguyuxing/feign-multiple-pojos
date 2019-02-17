package com.neo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-17 23:40
 **/
@Configuration
public class MethodArgumentResolver {
    @Autowired
    private RequestMappingHandlerAdapter adapter;

    //采用@PostConstruct 注解方式添加来定义解析器的顺序。解决Map类型参数被springmvc的MapMethodProcessor先处理了。
    @PostConstruct
    public void injectSelfMethodArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new JsonObjectArgResolverHandler());
        argumentResolvers.addAll(adapter.getArgumentResolvers());
        adapter.setArgumentResolvers(argumentResolvers);
    }
}