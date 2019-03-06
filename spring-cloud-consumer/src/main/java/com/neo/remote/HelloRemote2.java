package com.neo.remote;

import com.neo.config.ToJsonExpander;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

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
                         @Param(value = "name") String name,
                         @Param(value = "number") int number,
                         @Param(value = "number2")Integer number2,
                         @Param(value = "advertiser") Advertiser advertiser,
                         @Param(value = "material") Material material,
                         @Param(value = "date") Date date,
                         @Param(value = "materials") List<Material> materials,
                         @Param(value = "advertiserMap") Map<String, Advertiser> advertiserMap,
                         @Param(value= "nums") List<Integer> nums,
                         @Param(value= "map2") Map map2
                         );

    @RequestLine(value = "POST /hello4")
    public List<Integer> hello4(
            @Param(value = "name") String name,
//            @Param(value = "list") List<Integer> list,
            @Param(value = "file1") MultipartFile file1,
            @Param(value = "file2") MultipartFile file2,
            @Param(value = "files") MultipartFile[] files

    );
}


