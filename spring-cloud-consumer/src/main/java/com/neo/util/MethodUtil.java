package com.neo.util;

import feign.Param;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.MethodParametersAttribute;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import java.util.ArrayList;
import java.util.List;

//基于javaassist库，可以获取到抽象类和接口的方法参数名。项目编译时需开启debug模式并加上-parameter参数保留method元数据。
public class MethodUtil {

//
//    public static CtClass getCtClass(Class<?> parameterType) {
//        try {
//            ClassPool pool = ClassPool.getDefault();
//            CtClass ctClass = pool.get(parameterType.getName());
//            ctClass.stopPruning(true);
//            return  ctClass;
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取方法参数名称，按给定的参数类型匹配方法
//     *
//     * @param clazz
//     * @param method
//     * @param paramTypes
//     * @return
//     */
//    public static String[] getMethodParamNames(Class<?> clazz, String method,
//                                               Class<?>... paramTypes) {
//
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = null;
//        CtMethod cm = null;
//        try {
//            cc = pool.get(clazz.getName());
//
//            String[] paramTypeNames = new String[paramTypes.length];
//            for (int i = 0; i < paramTypes.length; i++) {
//                paramTypeNames[i] = paramTypes[i].getName();
//            }
//
//            cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
//        } catch (NotFoundException e) {
//
//        }
//        return getMethodParamNames(cm);
//    }
//
//    /**
//     * 根据方法对象获取方法参数名称，
//     *
//     * @param method
//     * @return
//     */
//    public static String[] getMethodParamNames(Method method) {
//
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = null;
//        CtMethod cm = null;
//        try {
//            cc = pool.get(method.getDeclaringClass().getName());
//            Class []paramTypes = method.getParameterTypes();
//            String []paramTypeNames = new String[method.getParameterTypes().length];
//            for (int i = 0; i < paramTypes.length; i++) {
//                paramTypeNames[i] = paramTypes[i].getName();
//            }
//
//            cm = cc.getDeclaredMethod(method.getName(), pool.get(paramTypeNames));
//        } catch (NotFoundException e) {
//
//        }
//        return getMethodParamNames(cm);
//    }
//
//    /**
//     *
//     * <p>
//     * 获取方法参数名称
//     * </p>
//     *
//     * @param cm
//     * @return
//     */
//    protected static String[] getMethodParamNames(CtMethod cm) {
//        CtClass cc = cm.getDeclaringClass();
//        MethodInfo methodInfo = cm.getMethodInfo();
//        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//        if(codeAttribute == null){
//            throw new RuntimeException("error: codeAttribute" + cc.getName());
//        }
//        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//                .getAttribute(LocalVariableAttribute.tag);
//        if (attr == null) {
//            throw new RuntimeException("error: Ctclass.getAttr" + cc.getName());
//        }
//
//        String[] paramNames = null;
//        try {
//            paramNames = new String[cm.getParameterTypes().length];
//        } catch (NotFoundException e) {
//            throw new RuntimeException("Ctclass " + paramNames);
//        }
//
////        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
////        for (int i = 0; i < paramNames.length; i++) {
////            paramNames[i] = attr.variableName(i + pos);
////        }
//
//        //修复以上三行代码获取方法参数名可能不正确的代码
//        TreeMap<Integer, String> sortMap = new TreeMap<Integer, String>();
//        for (int i = 0; i < attr.tableLength(); i++){
//            sortMap.put(attr.index(i), attr.variableName(i));
//        }
//        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//        paramNames = Arrays.copyOfRange(sortMap.values().toArray(new String[0]), pos, paramNames.length + pos);
//
//        return paramNames;
//    }
//

    /**
     * 获取接口方法参数名称
     * @param clazz
     * @param funName
     */
    public static List<String> getInterfaceParamterName(Class<?> clazz, String funName){
        ClassPool pool = ClassPool.getDefault();
        List<String> paramNames = new ArrayList<>();
        CtClass ctClass = null;
        try {
            ctClass = pool.get(clazz.getName());
            CtMethod ctMethod = ctClass.getDeclaredMethod(funName);
            // 使用javassist的反射方法的参数名
            MethodInfo methodInfo = ctMethod.getMethodInfo();
            MethodParametersAttribute methodParameters= (MethodParametersAttribute)methodInfo.getAttribute("MethodParameters");
            ConstPool cPool = methodInfo.getConstPool();
            //获取参数的个数
            int paramCount =ctMethod.getParameterTypes().length;
            for (int i = 0; i < paramCount; i++) {
                String paramName = cPool.getUtf8Info(ByteArray.readU16bit(methodParameters.get(), i * 4 + 1));
                paramNames.add(paramName);
            }

            //TODO 利用javaAssit在此处动态修改注解值,并重写一份class文件。(通过java自带反射修改的注解，再通过method.getParameterAnnotations访问时仍然不会改变)。
            ParameterAnnotationsAttribute paraAnnoAttr  = (ParameterAnnotationsAttribute) methodInfo.getAttribute(ParameterAnnotationsAttribute.visibleTag);
            Annotation [][]paramAnnos = paraAnnoAttr.getAnnotations();
            for(int j = 0; j< paramAnnos.length; j++){
                for(Annotation paramAnno : paramAnnos[j]){

                    if(paramAnno.getTypeName().equals(Param.class.getTypeName())){
                        paramAnno.addMemberValue("value", new StringMemberValue(paramNames.get(j), cPool));
                        ParameterAnnotationsAttribute attribute3 = new ParameterAnnotationsAttribute(cPool, ParameterAnnotationsAttribute.visibleTag);
                        attribute3.setAnnotations(paramAnnos);
                        methodInfo.addAttribute(paraAnnoAttr);
                        System.out.println("paraAnnoAttr---:" +  paraAnnoAttr);

                    }

                }
            }

            paraAnnoAttr.setAnnotations(paramAnnos);
            ctClass.stopPruning(true);//阻止冻结后精简CtClass
            ctClass.defrost();//解冻
//          当前ClassLoader中必须尚未加载该实体。（同一个ClassLoader加载同一个类只会加载一次）
            EntityClassLoader loader = new EntityClassLoader(clazz.getClassLoader());
            Class c = ctClass.toClass(loader, null);
            ctClass.defrost();//解冻
            ctClass.stopPruning(true);//阻止冻结后精简CtClass

            ctClass.writeFile("D:/testFile");
            ctClass.defrost();//解冻
            ctClass.stopPruning(true);//阻止冻结后精简CtClass

            System.out.println(clazz + funName+"获取结束");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return paramNames;
    }

}
  