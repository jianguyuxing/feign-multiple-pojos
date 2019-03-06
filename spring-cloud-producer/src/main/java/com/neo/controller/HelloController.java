package com.neo.controller;

import com.alibaba.fastjson.JSONObject;
import com.neo.model.Advertiser;
import com.neo.model.Material;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @RequestMapping(value = "/hello3")
    public String index3(
            @RequestPart String name,
            @RequestPart int number,
            @RequestPart Integer number2,
            @RequestPart Advertiser advertiser,
            @RequestPart Material material,
            @RequestPart Date date,
            @RequestPart List<Material> materials,
            @RequestPart Map<String, Advertiser> advertiserMap,
            @RequestPart List nums,
            @RequestPart("map2") Map map2
            ) {
        String result = "hello3成功进入生产者 \n";
        result += " name: " + name;
        result += " number: " + number;
        result += " number2: " + number2;
        result += " \n ------------" + JSONObject.toJSONString(advertiser);
        result += " \n ------------ " + material;
        result += " \n ------------ " + date;
        result += " \n ------------ " + materials;
        result += " \n ------------ " + advertiserMap;
        result += " \n ------------ " + nums;
        result += " \n ------------ " + map2;
        return result;
    }

    @RequestMapping(value = "/hello4")
    public List<Integer> index4(
            @RequestParam String name,
//            @JsonArgument(name="list") List<Integer> list,
            @RequestPart MultipartFile file1,
            @RequestPart MultipartFile file2,
            @RequestPart MultipartFile[] files) {

        String result = "hello4成功进入生产者 \n";
//        String filePath = "D:\\testFile\\producer";
//        File localFile = new File(filePath + "\\1.jpg");
//        try {
//            if (!localFile.exists()) {
//                localFile.createNewFile();
//            }
//            if (file1 != null && !file1.isEmpty()) {
//                file1.transferTo(localFile);
//            }
//
//            File localFile2 = new File(filePath + "\\2.jpg");
//            if (!localFile2.exists()) {
//                localFile2.createNewFile();
//            }
//            if (file2 != null && !file2.isEmpty()) {
//                file2.transferTo(localFile2);
//            }

//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                int num = i + 100;
//                File localFile_i = new File(filePath + "\\" + num + ".jpg");
//                if (!localFile_i.exists()) {
//                    localFile_i.createNewFile();
//                }
//                if (file != null && !file.isEmpty()) {
//                    file.transferTo(localFile_i);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        List list = new ArrayList();
        list.add(2);
        result += list;
        System.out.println(result);
        return list;
    }

}