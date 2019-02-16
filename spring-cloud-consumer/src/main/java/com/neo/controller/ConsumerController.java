package com.neo.controller;

import com.neo.model.Advertiser;
import com.neo.model.Material;
import com.neo.remote.HelloRemote;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/hello2/{name}")
    public String index2(@PathVariable("name") String name) {
        return HelloRemote2.hello2(name);
    }

    @RequestMapping("/hello3/{name}")
    public String index3(@PathVariable(value = "name") String name, int number,
                         Advertiser advertiser, Material material,
                         Date date) {
        System.out.println("hello3成功进入消费者：");
        System.out.println("name: " + name);
        System.out.println("number: " + number);
        System.out.println("-- advertiser" + advertiser);
        System.out.println("-- material" + material);
//        Date date = new Date();
        System.out.println("-- date" + date);
        System.out.println("消费者参数完毕 --");
        return HelloRemote2.hello3(
                name,
                number,
                advertiser,
                material,
                date);
    }
}