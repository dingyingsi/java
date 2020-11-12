package com.dys.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class T02 {
    public static void main(String[] args) throws Exception {
        String path = "User";
        Class clazz = Class.forName(path);

        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        // Field[] fields = clazz.getFields();
        Field[] fields = clazz.getDeclaredFields();
        Field f = clazz.getDeclaredField("uname");
        System.out.println(fields.length);
        for (Field temp : fields) {
            System.out.println("temp = " + temp);
        }
        Method[] methods = clazz.getDeclaredMethods();
        Method m01 = clazz.getDeclaredMethod("getUname", (Class<?>[]) null);

        Method m02 = clazz.getDeclaredMethod("setUname", String.class);
        for (Method m : methods) {
            System.out.println("m = " + m);
        }

        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor c = clazz.getDeclaredConstructor(int.class, int.class, String.class);
        System.out.println("c = " + c);
        for (Constructor temp : constructors) {
            System.out.println("temp = " + temp);
        }

    }
}
