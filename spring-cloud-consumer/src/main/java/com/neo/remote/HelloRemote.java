package com.neo.remote;

import com.fasterxml.jackson.annotation.JsonProperty;
import feign.Body;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by summer on 2017/5/11.
 */
//@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote {

//    @RequestLine(value = "GET /hello")
//    public String hello(@Param(value = "name") String name);


}
