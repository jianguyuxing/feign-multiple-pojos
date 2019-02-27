package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

//    @Autowired
//    HelloRemote HelloRemote;
    @Autowired
    HelloRemote2 HelloRemote2;
	
//    @RequestMapping("/hello/{name}")
//    public String index(@PathVariable("name") String name) {
//        return HelloRemote.hello(name);
//    }

//    @RequestMapping("/hello2/{name}")
//    public String index2(@PathVariable("name") String name) {
//        return HelloRemote2.hello2(name);
//    }

    @RequestMapping("/hello4/{name}")
    public String index4(@PathVariable(value = "name") String name) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        System.out.println("hello4成功进入消费者：");
        List result =  HelloRemote2.hello4(name, list);
        return JSONObject.toJSON(result).toString();
    }
}