package com.neo.remote;

import com.neo.model.Advertiser;
import com.neo.model.Material;
import feign.Body;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote2 {

    @RequestMapping(value = "/hello2")
    public String hello2(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/hello3")
    public String hello3(@RequestParam(value = "name") String name,
                         @RequestParam(value="advertiser") Advertiser advertiser,
                         @RequestParam(value = "material") Material material
                         );
}
