package com.neo.remote;

import com.neo.config.ToJsonExpander;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by summer on 2017/5/11.
 */
@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote2 {

//    @RequestMapping(value = "/hello2")
//    public String hello2(@RequestParam(value = "name") String name);

    @RequestLine(value = "POST /hello3")
    public String hello3(
                         @Param(value = "name", expander = ToJsonExpander.class) String name,
                         @Param(value = "number", expander = ToJsonExpander.class) int number,
                         @Param(value = "number2", expander = ToJsonExpander.class)Integer number2,
                         @Param(value = "advertiser", expander = ToJsonExpander.class) Advertiser advertiser,
                         @Param(value = "material", expander = ToJsonExpander.class) Material material,
                         @Param(value = "date", expander = ToJsonExpander.class) Date date,
//                         @Param(value = "materials", expander = ToJsonExpander.class) List<Material> materials,
                         @Param(value = "advertiserMap", expander = ToJsonExpander.class) Map<String, Advertiser> advertiserMap
                         );
}
