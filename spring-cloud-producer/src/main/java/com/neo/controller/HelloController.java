package com.neo.controller;

import com.neo.model.Advertiser;
import com.neo.model.Material;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }

    @RequestMapping("/hello2")
    public String index2(@RequestParam String name) {
        return "22222222hello "+name+"，this is first messge";
    }

    @RequestMapping(value = "/hello3")
    public String index3(String name, Advertiser advertiser, Material material) {
        String result = "hello3成功进入 \n";
        result += " name: " + name;
        result += " \n ------------" + advertiser;
        result += " \n ------------ " + material;
        return result;
    }
}