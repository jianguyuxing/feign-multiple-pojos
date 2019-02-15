package com.neo.config;

import feign.Contract;
import org.springframework.cloud.netflix.feign.AnnotatedParameterProcessor;
import org.springframework.cloud.netflix.feign.annotation.PathVariableParameterProcessor;
import org.springframework.cloud.netflix.feign.annotation.RequestHeaderParameterProcessor;
import org.springframework.cloud.netflix.feign.annotation.RequestParamParameterProcessor;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-12 18:45
 **/
@Configuration
public class FeignConfiguration {

    //启用Fegin自定义注解
//    @Bean
//    public Contract feignContract(){
//        return new feign.Contract.Default();
//    }

    @Bean
    public Contract springMVCContract() {
        //默认使用springmvc注解，并扩展支持一些自定义的jsonArgument注解
        List<AnnotatedParameterProcessor> processors = new ArrayList<>();
//        processors.add(new JsonArgumentParameterProcessor());//自定义注解，解决feign传model问题
        processors.add(new PathVariableParameterProcessor());
        processors.add(new RequestHeaderParameterProcessor());
        processors.add(new RequestParamParameterProcessor());
        return new SpringMvcContract(processors);
    }

//    @Bean
//    public CharlesRequestInterceptor charlesRequestInterceptor(){
//        return new CharlesRequestInterceptor();
//    }

}
