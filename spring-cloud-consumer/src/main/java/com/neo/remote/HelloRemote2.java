package com.neo.remote;

import com.neo.model.Advertiser;
import com.neo.model.Material;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;


@FeignClient(name = "spring-cloud-producer")
public interface HelloRemote2 {

    @RequestLine(value = "GET producer/hello2")
    String hello2(@Param(value = "name") String name);

    @RequestLine(value = "GET producer/hello3")
    String hello3(@Param(value = "name") String name, @Param(value = "advertiser") Advertiser advertiser, @Param(value = "material") Material material,
                  @Param(value = "date") Date date, @Param(value = "materials") List<Material> materials, @Param(value = "advertiserMap") Map<String, Advertiser> advertiserMap);

    @RequestLine(value = "POST producer/hello4")
    List<Integer> hello4(@Param(value = "name") String name, @Param(value = "number") Integer number, @Param(value = "file1") MultipartFile file1, @Param(value = "file2") MultipartFile file2, @Param(value = "files") MultipartFile[] files);
}


