package com.dys.reflection;

import com.dys.java8.stream.Demo04;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class T04 {
    public static void main(String[] args) {

        try {
            Method m = Demo04.class.getMethod("test01", Map.class, List.class);
            Type[] t = m.getGenericParameterTypes();
            for (Type paramType : t) {
                System.out.println("#" + paramType);
                if (paramType instanceof ParameterizedType) {
                    Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
                    for (Type genericType : genericTypes) {
                        System.out.println("genericType = " + genericType);
                    }
                }
            }
            Method m2 = Demo04.class.getMethod("test02", (Class<?>[]) null);
            Type returnType = m2.getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) returnType).getActualTypeArguments();
                for (Type genericType : genericTypes) {
                    System.out.println("genericType = " + genericType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
