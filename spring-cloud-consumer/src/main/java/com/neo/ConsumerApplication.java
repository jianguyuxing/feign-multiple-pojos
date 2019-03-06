package com.neo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.config.FeignSpringFormEncoder;
import feign.codec.Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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

//	//文件上传支持 与springboot中默认的multipartResolver冲突，待会关闭默认再开启。
//	@Bean(name = "multipartResolver")
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		//set the max upload size 100MB
//		multipartResolver.setMaxUploadSize(1024000000);
//		multipartResolver.setDefaultEncoding("utf-8");
//		multipartResolver.setMaxInMemorySize(1024);
//		try {
//			multipartResolver.setUploadTempDir(new FileSystemResource("D:/testFile/temp"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return multipartResolver;
//	}


	//feign 发送文件编码器
	@Bean
	public Encoder FeignSpringFormEncoder(){
		return new FeignSpringFormEncoder();
	}



}
