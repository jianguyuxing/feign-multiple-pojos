package com.neo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Param;
import org.springframework.stereotype.Component;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-17 20:28
 **/
@Component
public class ToJsonExpander implements Param.Expander {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String expand(Object value) {
        Object json  = JSONObject.toJSON(value);
        String str = null;
        if(json.getClass().isInstance(new String())){
            //本身是字符串的再调用会在双引号外多加双引号，所以不再调用toJsonString方法
            str = json + "";
        }else {
            str = JSONObject.toJSONString(value,  SerializerFeature.WriteNonStringValueAsString);
        }

        return str;
    }
}