package com.neo.config;

import com.alibaba.fastjson.JSONObject;
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
            String str  = JSONObject.toJSONString(value);
            return str;
    }
}