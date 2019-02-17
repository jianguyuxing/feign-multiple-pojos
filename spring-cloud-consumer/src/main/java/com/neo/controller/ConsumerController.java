package com.neo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import com.neo.remote.HelloRemote;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @RequestMapping("/hello3/{name}")
    public String index3(@PathVariable(value = "name") String name, int number,
                         Integer number2,
                         Advertiser advertiser, Material material,
                         Date date) {
        System.out.println("hello3成功进入消费者：");
        System.out.println("name: " + name);
        System.out.println("number: " + number);
        System.out.println("number2:" + number2);
        System.out.println("-- advertiser" + advertiser);
        System.out.println("-- material" + material);
//        Date date = new Date();


        List materials = new ArrayList();
        Material materialListField = new Material();
        materialListField.setSource("MateriaListSource");
        materialListField.setTitle("MateriaListTitle");
        Material materialListField2 = new Material();
        materialListField2.setSource("MateriaListSource2");
        materialListField2.setTitle("MateriaListTitle2");
        materials.add(materialListField);
        materials.add(materialListField2);

        Advertiser a1 = new Advertiser();
        a1.setCompanyName("a1CName");
        Advertiser a2 = new Advertiser();
        a2.setCompanyName("a2CName");
        Map<String, Advertiser> advertiserMap = new HashMap<>();
        advertiserMap.put("a1", a1);
        advertiserMap.put("a2", a2);

        Set set = new HashSet();
        set.add(materialListField);
        set.add(a1);
        System.out.println("set :" + set);
        System.out.println("setJson:" + JSONObject.toJSONString(set));

        System.out.println("materials:" + materials);
        System.out.println("-- date" + date);
        System.out.println("---advertiserMap: " + advertiserMap);
        System.out.println("消费者参数完毕 --");
        return HelloRemote2.hello3(
                name,
                number,
                number2,
                advertiser,
                material,
                date,
//                materials,
                advertiserMap);
    }
}