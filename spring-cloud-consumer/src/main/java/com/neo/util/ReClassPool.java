package com.neo.util;

import javassist.ClassPool;
import javassist.CtClass;

public class ReClassPool extends ClassPool {
    //覆盖ClassPool的removeCached方法，移除ClassPool中的缓存Class

	@Override
    public CtClass removeCached(String classname) {
        return (CtClass)classes.remove(classname);
    }
}