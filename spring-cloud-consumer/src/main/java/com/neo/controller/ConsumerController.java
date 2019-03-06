package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.IntrospectorCleanupListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String index3(@PathVariable String name) {
//        String name = "testName";
        List list = new ArrayList();
        list.add(1);
        list.add(2);

        int number = 22;
        Integer number2 = 33;
        Advertiser advertiser = new Advertiser();
        advertiser.setCompanyName("testComp");
        advertiser.setTestAdvNum(1111);

        Advertiser advertiser2 = new Advertiser();
        advertiser2.setCompanyName("testComp2");
        advertiser.setTestAdvNum(2222);

//--------------------------------------

        Material material = new Material();
        material.setTitle("tit");
        material.setSource("srce");
        material.setDuration(111111);

        Material material2 = new Material();
        material2.setTitle("tit2");
        material2.setSource("srce2");
        material.setDuration(22222);


        Date date = new Date();
        Material[] materialArr = new Material[]{material, material2};
        List materials = Arrays.asList(materialArr);
        Map<String, Advertiser> advertiserMap = new HashMap<>();
        advertiserMap.put("adv1", advertiser);

        int [] numArr = {1,22,33};
        List nums =  Arrays.asList(numArr);

        Map map2 =  new HashMap();
        map2.put("1", 111);
        map2.put(2, "222");

        System.out.println("hello3成功进入消费者：");
        String result =  HelloRemote2.hello3(name, number, number2, advertiser, material, date, materials, advertiserMap, nums, map2);
        return JSONObject.toJSON(result).toString();
    }

    @RequestMapping("/hello4/{name}")
    public String index4(MultipartFile file1, MultipartFile file2) {
        String name = "testName";
        List list = new ArrayList();
        list.add(1);
        list.add(2);

//        MultipartFile file1 = request.getFile("file");
//        Iterator<String> names = request.getFileNames();
//        while (names.hasNext()){
//            String fileName = names.next();
//            System.out.println("文件名: " + fileName);
//        }
        String filePath = "D:\\testFile";
        File localFile = new File(filePath + "\\1.jpg");
        try {
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            if (file1 != null && !file1.isEmpty()) {
                file1.transferTo(localFile);
            }

            File localFile2 = new File(filePath + "\\2.jpg");
            if (!localFile2.exists()) {
                localFile2.createNewFile();
            }
            if (file2 != null && !file2.isEmpty()) {
                file2.transferTo(localFile2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultipartFile[] files = {file1, file2};

        System.out.println("hello4成功进入消费者：");
        List<Integer> result =  HelloRemote2.hello4(name, file1, file2, files);

        return JSONObject.toJSON(result).toString();
    }

    //Spring 刷新Introspector防止内存泄露
    @Bean
    public IntrospectorCleanupListener introspectorCleanupListener(){
        return new IntrospectorCleanupListener();
    }

}