package com.neo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-16 18:06
 **/
@Component
public class JsonObjectArgResolverHandler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonObject.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        // 获取Controller中的参数名
        String name = methodParameter.getParameterName();
        String value = nativeWebRequest.getParameter(name);
        // 获取Controller中参数的类型
        Class clazz = methodParameter.getParameterType();

        if(clazz == String.class || isBaseType(clazz)){
           return dealWithPrimaryType(clazz, value);
        }

        if(clazz == Date.class){
            DateFormat dateFormat =  new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",
//                    new Locale("zh-CN", "CHINA"),
                    new Locale("en"));
            Date date  = dateFormat.parse(value);
            return date;
        }

        // 实例化
        Object target = clazz.newInstance();

        //是对象或数组时
        if(JsonUtil.isJsonObject(value) || JsonUtil.isJsonArray(value)){
            JSON json = JSONObject.parseObject(value);
            Object o = JSONObject.toJavaObject(json, clazz);
            BeanUtils.copyProperties(o, target);
        }

        return target;
    }

    private Object dealWithPrimaryType(Class clz, String value) {
                if (clz.equals(String.class)){
                    return value;
                }

                if(clz.equals(int.class) ){
                    return Integer.parseInt(value);
                }
                if(clz.equals(byte.class) ){
                    try {
                       return value.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                if(clz.equals(long.class) ){
                    return Long.parseLong(value);
                }
                if(clz.equals(double.class) ){
                    return Double.parseDouble(value);
                }

                if(clz.equals(float.class) ){
                    return Float.parseFloat(value);
                }
                if(clz.equals(char.class) ){
                    return value.toCharArray()[0];
                }
                if(clz.equals(short.class) ){
                    return Short.parseShort(value);
                }
                if(clz.equals(boolean.class) ){
                    return Boolean.parseBoolean(value);
                }
                return value;
    }

    /**
     * 判断object是否为基本类型
     * @return
     */
    public static boolean isBaseType(Class clz) {

        if (    clz.equals(int.class) ||
                clz.equals(byte.class) ||
                clz.equals(long.class) ||
                clz.equals(double.class) ||
                clz.equals(float.class) ||
                clz.equals(char.class) ||
                clz.equals(short.class) ||
                clz.equals(boolean.class)) {
            return true;
        }
        return false;
    }
}