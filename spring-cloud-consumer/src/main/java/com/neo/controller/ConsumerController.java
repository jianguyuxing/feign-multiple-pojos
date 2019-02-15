package com.neo.controller;

import com.neo.model.Advertiser;
import com.neo.model.Material;
import com.neo.remote.HelloRemote;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;
    @Autowired
    HelloRemote2 HelloRemote2;
	
    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloRemote.hello(name);
    }

    @RequestMapping("/hello2/{name}")
    public String index2(@PathVariable("name") String name) {
        return HelloRemote2.hello2(name);
    }

    @RequestMapping("/hello3/{name}")
    public String index3(@PathVariable(value = "name") String name, Advertiser advertiser, Material material) {
        System.out.println("name: " + name);
        return HelloRemote2.hello3(name, advertiser, material);
    }
}