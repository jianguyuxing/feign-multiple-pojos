package com.neo.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configurable
public class CharlesRequestInterceptor implements RequestInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {

        //GET请求时此处对集合类型进行处理.
//        Map<String, Collection<String>> map = template.queries();
//        Map<String, Collection<String>> newMap = new LinkedHashMap<>();
//        for(Map.Entry<String, Collection<String>> entry : map.entrySet()){
//            String key = null;
//            String val = null;
//            List list= new ArrayList();
//            try {
//                key = URLEncoder.encode(entry.getKey(), "UTF-8");
//                List<String> vals = (List) entry.getValue();
//                if(vals.size() <=1){
//                    val = URLEncoder.encode(vals.get(0), "UTF-8");
//                }else{
//                    //把key对应的list合并到一个值中
//                    val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
//                }
//
//                list.add(val);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            newMap.put(key, list);
//
//        }
//        template.queries(newMap);

        //POST 时对body进行处理，map等类型只有在body获取了然后放到queries。(否则应该在获取到之前对map进行Json处理防止变成多个参数。)
        byte[] bytes = template.body();
        Map<String, Collection<String>> newMap = new LinkedHashMap<>();

        try {
            Object obj = JSONObject.parse(new String(bytes, "UTF-8"));
            Map<String, Object> bodyMap = (Map) obj;

            for(Map.Entry<String, Object> entry : bodyMap.entrySet()){
                String key = null;
                String val = null;
                List list= new ArrayList();
                try {
                    key = URLEncoder.encode(entry.getKey(), "UTF-8");
                    List<String> vals = new ArrayList<>();
                    if(String.class == entry.getValue().getClass()){
                        val =  URLEncoder.encode(entry.getValue().toString(),  "UTF-8");
                    }

                    if(Collection.class.isAssignableFrom(entry.getValue().getClass())){
                        vals = (List) entry.getValue();
                        if(vals.size() <=1){
                            val = URLEncoder.encode(vals.get(0), "UTF-8");
                        }else{
                            //把key对应的list合并到一个值中
                            val = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                        }
                    }


                    list.add(val);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                newMap.put(key, list);

            }
            template.queries(newMap);
            template.body(null);
            System.out.println(obj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(template);
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) {   // 叶子节点
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) {   // 数组节点
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {  // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}