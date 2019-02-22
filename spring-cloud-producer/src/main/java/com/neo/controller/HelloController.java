package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.config.JsonArgument;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
	
    @RequestMapping("/hello")
    public String index(@RequestParam(value = "name")String name) {
        return "hello "+name+"，this is first messge";
    }

    @RequestMapping("/hello2")
    public String index2(@RequestParam(value = "name") String name) {
        return "22222222hello "+name+"，this is first messge";
    }

    @RequestMapping(value = "/hello3", consumes = "application/json")
    public String index3(
            @JsonArgument String name,
            @JsonArgument int number,
            @JsonArgument Integer number2,
            @JsonArgument Advertiser advertiser,
            @JsonArgument Material material,
            @JsonArgument Date date,
//            @JsonArgument List<Material> materials,
            @JsonArgument Map<String, Advertiser> advertiserMap
            ) {
        String result = "hello3成功进入生产者 \n";
        result += " name: " + name;
        result += " number: " + number;
        result += " number2: " + number2;
        result += " \n ------------" + JSONObject.toJSONString(advertiser);
        result += " \n ------------ " + material;
        result += " \n ------------ " + date;
//        result += " \n ------------ " + materials;
        result += " \n ------------ " + advertiserMap;
        return result;
    }

    @RequestMapping(value = "/hello4", consumes = "application/json")
    public List<Integer> index4(
            @JsonArgument String name,
            @JsonArgument List<Integer> list) {

        String result = "hello4成功进入生产者 \n";
        result += list;
        System.out.println(result);
        return list;
    }

}