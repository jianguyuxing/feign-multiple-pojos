package com.neo.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-12 18:45
 **/
@Configuration
public class FeignConfiguration {


//    启用Fegin自定义注解
//    @Bean
//    public Contract feignContract(){
//        return new feign.Contract.Default();
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

    //解决feign不支持header
//    @Bean
//    public RequestInterceptor headerInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                        .getRequestAttributes();
//                HttpServletRequest request = attributes.getRequest();
//                Enumeration<String> headerNames = request.getHeaderNames();
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        String values = request.getHeader(name);
//                        requestTemplate.header(name, values);
//                    }
//                }
//            }
//        };
//    }

}





