package com.neo.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//现在不采用该方案，因为无法发送大文件及大对象，留作案例备忘
public class CharlesRequestInterceptor implements RequestInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {


        //采用POST发送, 并对body进行处理，map等类型只有在body获取了然后放到queries。(否则应该在获取到之前对map进行Json处理防止变成多个参数。)
        byte[] bytes = template.body();
        Map<String, Collection<String>> newMap = new LinkedHashMap<>();

        if(bytes == null || bytes.length <= 0){
            //没有body，只有querie 时
            return;
        }

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
                        if (vals == null || vals.isEmpty()){
                            //TODO
                            val = null;
                        }else if(vals.size() == 1){

                            val = vals.get(0);
                        }else{
                            //把key对应的list合并到一个值中
                            val = entry.getValue().toString();
                        }
                    }

                    list.add(val);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                newMap.put(key, list);

            }
            template.queries(newMap).resolve(newMap);
            template.body(null);
//            System.out.println(obj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        System.out.println(template);
    }

}