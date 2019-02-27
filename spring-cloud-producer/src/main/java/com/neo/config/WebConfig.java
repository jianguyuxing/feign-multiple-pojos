package com.neo.config;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-16 18:07
 **/

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{

    //该类作废， 采用@PostConstruct 注解方式添加来定义解析器的顺序。解决Map类型参数被springmvc的MapMethodProcessor先处理了。

//    @Autowired
//    JsonObjectArgResolverHandler jsonObjectArgResolverHandler;
//    @Override
////    参数解析器，被框架回调
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//
//        argumentResolvers.add(jsonObjectArgResolverHandler);
//    }

}