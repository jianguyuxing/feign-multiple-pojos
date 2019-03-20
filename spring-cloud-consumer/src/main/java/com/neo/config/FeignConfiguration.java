package com.neo.config;

import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-12 18:45
 **/
@Configuration
public class FeignConfiguration {


//// 启用Fegin自定义注解
//    @Bean
//    public Contract feignContract(){
//        //重写了feign.Contract.Default 的相关方法。解决@Param参数必须填value的问题
//        return new MyFeignContract();
//    }

    //// 启用Fegin自定义注解, 以开启@Param、@RequestLine注解
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

//	//feign接收pojo解码器
//	@Bean
//	public Decoder JacksonDecoder(){
//		return new JacksonDecoder();
//	}

//    @Bean
//    public RequestInterceptor requestInterceptor(){
//        return new CharlesRequestInterceptor();
//    }

//    @Bean
//    public Contract springMVCContract() {
//        //，并扩展支持一些自定义的jsonArgument注解
////        List<AnnotatedParameterProcessor> processors = new ArrayList<>();
//////        processors.add(new JsonArgumentParameterProcessor());//自定义注解，解决feign传model问题
////        processors.add(new PathVariableParameterProcessor());
////        processors.add(new RequestHeaderParameterProcessor());
////        processors.add(new RequestParamParameterProcessor());
////        return new SpringMvcContract(processors);
//        return new SpringMvcContract();
//    }


}





