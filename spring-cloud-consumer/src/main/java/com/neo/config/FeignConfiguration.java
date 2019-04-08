package com.neo.config;

import feign.Contract;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-12 18:45
 **/
@Configuration
public class FeignConfiguration {


    // 启用Fegin自带注解, 如@Param、@RequestLine,必须
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }


    //feign 发送文件编码器  an Encoder support Mutiple poJos And MutipartFile Array
    @Bean
    public Encoder FeignSpringFormEncoder(){
        return new FeignSpringFormEncoder();
    }


}





