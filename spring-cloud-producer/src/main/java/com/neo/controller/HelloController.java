package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
	
    @RequestMapping("/producer/hello")
    public String index(@RequestParam(value = "name")String name) {
        return "hello "+name+", this is first messge";
    }

    @RequestMapping("/producer/hello2")
    public String index2(@RequestParam(value = "name") String name) {
        return "hello2 "+name+", this is first messge";
    }

    @RequestMapping(value = "/producer/hello3")
    public String index3(
            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "advertiser", required = false) Advertiser advertiser,
            @RequestPart(value = "material", required = false) Material material,
            @RequestPart(value = "date", required = false) Date date,
            @RequestPart(value = "materials", required = false) List<Material> materials,
            @RequestPart(value = "advertiserMap", required = false) Map<String, Advertiser> advertiserMap
            ) {
        String result = "hello3 Producer in \n";
        result += " name: " + name;
        result += " \n ------------" + JSONObject.toJSONString(advertiser);
        result += " \n ------------ " + material;
        result += " \n ------------ " + date;
        result += " \n ------------ " + materials;
        result += " \n ------------ " + advertiserMap;
        return result;
    }

    @RequestMapping(value = "/producer/hello4")
    public List<Integer> index4(
            @RequestParam String name,
            @RequestPart(value = "number", required = false) Integer number,
            @RequestPart(value = "file1", required = false) MultipartFile file1,
            @RequestPart(value = "file2", required = false) MultipartFile file2,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {

        String result = "hello4 producer in name: " + name + " number: " + number;
        String filePath = "D:\\testFile\\producer";
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

            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                int num = i + 100;
                File localFile_i = new File(filePath + "\\" + num + ".jpg");
                if (!localFile_i.exists()) {
                    localFile_i.createNewFile();
                }
                if (file != null && !file.isEmpty()) {
                    file.transferTo(localFile_i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        List<Integer> list = new ArrayList(Arrays.asList(new Integer[]{1,2}));
        return list;
    }

}