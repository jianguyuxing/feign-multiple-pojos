package com.neo.config;

import com.neo.util.MethodUtil;
import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Gu Yuxing
 * @Create 2019-03-06 20:47
 **/
public class MyFeignContract extends Contract.Default{

    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation,
                                             Method method) {
//        Class<? extends Annotation> annotationType = methodAnnotation.annotationType();
//        if (annotationType == RequestLine.class) {
//            String requestLine = RequestLine.class.cast(methodAnnotation).value();
//            checkState(emptyToNull(requestLine) != null,
//                    "RequestLine annotation was empty on method %s.", method.getName());
//            if (requestLine.indexOf(' ') == -1) {
//                checkState(requestLine.indexOf('/') == -1,
//                        "RequestLine annotation didn't start with an HTTP verb on method %s.",
//                        method.getName());
//                data.template().method(requestLine);
//                return;
//            }
//            data.template().method(requestLine.substring(0, requestLine.indexOf(' ')));
//            if (requestLine.indexOf(' ') == requestLine.lastIndexOf(' ')) {
//                // no HTTP version is ok
//                data.template().append(requestLine.substring(requestLine.indexOf(' ') + 1));
//            } else {
//                // skip HTTP version
//                data.template().append(
//                        requestLine.substring(requestLine.indexOf(' ') + 1, requestLine.lastIndexOf(' ')));
//            }
//
//            data.template().decodeSlash(RequestLine.class.cast(methodAnnotation).decodeSlash());
//            data.template().collectionFormat(RequestLine.class.cast(methodAnnotation).collectionFormat());
//
//        } else if (annotationType == Body.class) {
//            String body = Body.class.cast(methodAnnotation).value();
//            checkState(emptyToNull(body) != null, "Body annotation was empty on method %s.",
//                    method.getName());
//            if (body.indexOf('{') == -1) {
//                data.template().body(body);
//            } else {
//                data.template().bodyTemplate(body);
//            }
//        } else if (annotationType == Headers.class) {
//            String[] headersOnMethod = Headers.class.cast(methodAnnotation).value();
//            checkState(headersOnMethod.length > 0, "Headers annotation was empty on method %s.",
//                    method.getName());
//            data.template().headers(toMap(headersOnMethod));
//        }

        customProcessMethod(data, methodAnnotation, method);
    }

    /**
     * 将方法参数名注入到@Param注解中
     * @param data
     * @param methodAnnotation
     * @param method
     */
    private void customProcessMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        super.processAnnotationOnMethod(data, methodAnnotation, method);
        List paramNames = MethodUtil.getInterfaceParamterName(method.getDeclaringClass(), method.getName());
        Parameter[] parameters = method.getParameters();
////            //TODO  这种替换方式替换是无效的，因为method.getParameterAnnotations获取的anno值不会变
//            Annotation [][]parameterAnnotations = method.getParameterAnnotations();
//            int count = parameterAnnotations.length;
//            for (int i = 0; i < count; i++) {
//                Annotation[] annoArr = parameterAnnotations[i];
//                for(Annotation anno: annoArr){
//                    if(anno != null ){
//                        InvocationHandler invocationHandler = Proxy.getInvocationHandler(anno);
//                        Field values = invocationHandler.getClass().getDeclaredField("memberValues");
//                        values.setAccessible(true);
//                        Map<String, Object> memberValues =(Map<String, Object>) values.get(invocationHandler);
//                        String val = (String) memberValues.get("value");
//                        System.out.println("改变前:"  + val);
//                        memberValues.put("value",  parameters[i].getName());
//                        System.out.println("改变后:"  + ((Param)anno).value());
//                      }
//                }
//            }
//            System.out.println("---------");
    }

    private static Map<String, Collection<String>> toMap(String[] input) {
        Map<String, Collection<String>>
                result =
                new LinkedHashMap<String, Collection<String>>(input.length);
        for (String header : input) {
            int colon = header.indexOf(':');
            String name = header.substring(0, colon);
            if (!result.containsKey(name)) {
                result.put(name, new ArrayList<String>(1));
            }
            result.get(name).add(header.substring(colon + 2));
        }
        return result;
    }
}
