package com.neo.remote;

import com.neo.config.FeignConfiguration;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote2 {

    @RequestMapping(value = "/hello2")
    public String hello2(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/hello3", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public String hello3(
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "number") int number,
                         @RequestParam(value = "advertiser") Advertiser advertiser,
                         @RequestParam(value = "material") Material material,
                         @RequestParam(value = "date") Date date
                         );
}
