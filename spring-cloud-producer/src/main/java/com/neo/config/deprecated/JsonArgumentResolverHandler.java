package com.neo.config.deprecated;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neo.common.WdspException;
import com.neo.common.WdspMultipartFile;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Gu Yuxing
 * @Create 2019-02-16 18:06
 **/
//@Component
public class JsonArgumentResolverHandler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //此处注明哪些条件下的采用该参数解析器。
        //有JsonArgument注解的采用该解析器
        return methodParameter.hasParameterAnnotation(JsonArgument.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        // 获取Controller中的参数名
        String paramName = methodParameter.getParameterName();
        JsonArgument jsonAnno = methodParameter.getParameterAnnotation(JsonArgument.class);
        if(StringUtils.hasText(jsonAnno.value())){
            //优先取注解中的参数值
            paramName = jsonAnno.value();
        }

        String value = nativeWebRequest.getParameter(paramName);

        if (value == null) {
            if(jsonAnno.required() == true ){
                throw new WdspException("required param: \"" + paramName + "\"not supported");
            }
            return null;
        }

        value = URLDecoder.decode(value, "UTF-8");
        // 获取Controller中参数的类型
        Class clazz = methodParameter.getParameterType();

        if(clazz == String.class || isBaseTypeOrBaseWrapper(clazz)){
            return dealWithSimpleType(clazz, value);
        }

        if(clazz == Date.class){
            //根据传入格式处理
//            DateFormat dateFormat =  new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",
//                    new Locale("en"));
//            Date date  = dateFormat.parse(value);

            //传入是时间戳时
            Date date = new Date(Long.parseLong(value));
            return date;
        }

        //集合
        if(Collection.class.isAssignableFrom(clazz) ){
            if(Set.class.isAssignableFrom(clazz)){
                List list = dealWithColletion(methodParameter, value);
                return new LinkedHashSet<>(list);
            }else if (List.class.isAssignableFrom(clazz) || Collection.class == clazz){
                return dealWithColletion(methodParameter, value);

            }
        }

        //Map
        if(Map.class.isAssignableFrom(clazz)){
            return JSONObject.parse(value);
        }

        //接收类型是数组或变长参数
        if(clazz.isArray()){
            Class elementType = clazz.getComponentType();
            List list = JSONObject.parseArray(value, elementType);
            int size = list == null ? 0 : list.size();
            Object[] arr =(Object[]) Array.newInstance(elementType, size);
            if(list != null){
                arr =  list.toArray(arr);
            }
            return arr;
        }

        //TODO 测试文件能否解析，这个应该看看是否能在发送端解决。
        if(MultipartFile.class.isAssignableFrom(clazz)){
            JSONObject jsonObj = JSONObject.parseObject(value);
            String name = jsonObj.get("name") + "";
            String originalFilename = jsonObj.get("originalFilename") + "";
            String contentType = jsonObj.get("contentType") + "";
            //发送端已将byte数组转为字符串发送
            String bytesStr = (String) jsonObj.get("bytes");

            byte[] arr = new byte[]{};
            if (StringUtils.hasText(bytesStr)){
                //发送端已将byte数组转为字符串发送
                //二进制文件byte数组转为字符串传输时需要进行base64等编解码
                arr = new BASE64Decoder().decodeBuffer(bytesStr);
                //处理数据
                for (int i = 0;i<arr.length;++i){
                    if(arr[i]<0){
                        arr[i]+=256;
                    }
                }
                jsonObj.put("bytes", arr);
            }
            WdspMultipartFile file = new WdspMultipartFile(name , originalFilename , contentType , arr);
            BeanUtils.copyProperties(jsonObj, file);
            return file;
        }


        //普通POJO

        // 实例化
        Object target = clazz.newInstance();

        //是对象
        if(JsonUtil.isJsonObject(value)){
            JSON json = JSONObject.parseObject(value);
            Object o = JSONObject.toJavaObject(json, clazz);
            BeanUtils.copyProperties(o, target);
        }

        return target;
    }

    /**
     * 处理集合类型
     * @param methodParameter
     * @param value
     * @return
     * @throws ClassNotFoundException
     */
    private List dealWithColletion(MethodParameter methodParameter, String value) throws ClassNotFoundException {
        //FIXME 这个应该在feignClient发送端解决,防止一些问题，比如feignCient将Integer的list编码为了String的list，而接收端没有标明泛型。
        List list = new ArrayList<>();
        Object obj = null;
        ParameterizedType type  = (ParameterizedType)methodParameter.getGenericParameterType();
        Type[]types = type.getActualTypeArguments();
        if (types.length > 0){
            //接收参数集合有泛型定义
            Type genericType = types[0];
            String typeName = genericType.getTypeName();
            Class genericClz =  Class.forName(typeName);
            boolean isArr = JsonUtil.isJsonArray(value);
//                    boolean isObj = JsonUtil.isJsonObject(value);
            if(isArr){
                obj = JSONObject.parseArray(value, genericClz);
            }else {
                //value 除了是数组、对象外，还可能是字符串等
                obj = JSONObject.parseObject(value, genericClz);
            }

        }else {
            obj = JSONObject.parse(value);
        }

        if(List.class.isAssignableFrom(obj.getClass())){
            list = (List)obj;
        } else {
            list.add(obj);

        }
        return list;
    }

    /**
     * 处理基础类型包括String
     * @param clz
     * @param value
     * @return
     */
    private Object dealWithSimpleType(Class clz, String value) {
        if (clz.equals(String.class)){
            return value;
        }

        if(clz.equals(int.class) || clz.equals(Integer.class)){
            return Integer.parseInt(value);
        }
        if(clz.equals(byte.class) || clz.equals(Byte.class)){
            try {
                return value.getBytes("UTF-8")[0];
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(clz.equals(long.class) || clz.equals(Long.class)){
            return Long.parseLong(value);
        }
        if(clz.equals(double.class) || clz.equals(Double.class)){
            return Double.parseDouble(value);
        }

        if(clz.equals(float.class) || clz.equals(Float.class)){
            return Float.parseFloat(value);
        }
        if(clz.equals(char.class) || clz.equals(Character.class)){
            return value.toCharArray()[0];
        }
        if(clz.equals(short.class) || clz.equals(Short.class)){
            return Short.parseShort(value);
        }
        if(clz.equals(boolean.class) || clz.equals(Boolean.class)){
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    /**
     * 判断object是否为基础类型或包装类
     * @return
     */
    public static boolean isBaseTypeOrBaseWrapper(Class clz) {

        if (    clz.equals(int.class) ||clz.equals(Integer.class) ||
                clz.equals(byte.class) ||clz.equals(Byte.class) ||
                clz.equals(long.class) ||clz.equals(Long.class) ||
                clz.equals(double.class) ||clz.equals(Double.class) ||
                clz.equals(float.class) ||clz.equals(Float.class) ||
                clz.equals(char.class) ||clz.equals(Character.class) ||
                clz.equals(short.class) ||clz.equals(Short.class) ||
                clz.equals(boolean.class) || clz.equals(Boolean.class)
        ){
            return true;
        }
        return false;
    }

    protected Object createAttribute(MethodParameter methodParam) throws Exception {

        return BeanUtils.instantiateClass(methodParam.getParameterType());
    }

}