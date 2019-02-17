package com.neo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	//TODO 下一步需要扩展下expander，在feignclient端，对对象进行转化时不依赖于toString方法。这样也能处理集合等类型。

	@Bean
	public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		//设置日期格式
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:ss");
		objectMapper.setDateFormat(smt);
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
		//设置中文编码格式
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON_UTF8);
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
		return mappingJackson2HttpMessageConverter;
	}


}
