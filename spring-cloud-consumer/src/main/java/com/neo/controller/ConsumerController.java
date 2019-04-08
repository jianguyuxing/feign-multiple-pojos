package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import com.neo.remote.HelloRemote2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    HelloRemote2 helloRemote2;

    @RequestMapping("/hello2/{name}")
    public String index2(@PathVariable("name") String name) {
        return helloRemote2.hello2(name);
    }

    @RequestMapping("/hello3/{name}")
    public String index3(@PathVariable String name) {
        Advertiser advertiser = new Advertiser("testComp", 1);
        Advertiser advertiser2 = new Advertiser("testComp2", 2);
        Material material = new Material("tit","src1", 11);
        Material material2 = new Material("tit2","src2", 22);

        Material[] materialArr = new Material[]{material, material2};
        List materials = new ArrayList(Arrays.asList(materialArr));
        Map<String, Advertiser> advertiserMap = new HashMap<>();
        advertiserMap.put("adv1", advertiser);
        advertiserMap.put("adv2", advertiser2);

        System.out.println("hello3 consumer success in: ");
        String result = helloRemote2.hello3(name, advertiser, material, new Date(), materials, advertiserMap);
        return JSONObject.toJSON(result).toString();
    }

    @RequestMapping("/hello4/{name}")
    public String index4(MultipartFile file1, MultipartFile file2) {

//        MultipartFile file1 = request.getFile("file");
//        Iterator<String> names = request.getFileNames();
//        while (names.hasNext()){
//            String fileName = names.next();
//            System.out.println("fileName: " + fileName);
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

        System.out.println("hello4 consumer success in:");
        List<Integer> result = helloRemote2.hello4("testName", 100, file1, file2, files);
        return JSONObject.toJSON(result).toString();
    }


}