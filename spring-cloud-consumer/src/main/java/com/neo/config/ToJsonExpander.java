package com.neo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-17 20:28
 **/
//不启用，留作案例备忘
//@Component
public class ToJsonExpander implements Param.Expander {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String expand(Object value) {
        String str = null;
        try {
            Object json  = JSONObject.toJSON(value);
            str = null;
            if(value == null){
                return "";
            }
            if(json.getClass().isInstance(new String())){
                str = json + "";
            }else if(MultipartFile.class.isAssignableFrom(value.getClass())){
                MultipartFile file = (MultipartFile)value;
                byte[] bytes = file.getBytes();
                String byteStr = "";
                if(bytes != null && bytes.length > 0){
                    //二进制文件采用字符串形式传输需经过base64等编解码
                    byteStr = new BASE64Encoder().encode(bytes);
                    ((JSONObject) json).put("bytes", byteStr);
                    Object result = JSONObject.toJSON(json);
                    str = result + "";
                }

            }
            else {
                str = JSONObject.toJSONString(value,  SerializerFeature.WriteNonStringValueAsString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}