package com.dys.reflection;

import java.lang.reflect.Field;

public class T06 {
    public static void main(String[] args) {
        Class clazz = Class.forName("com.bjsxt.test.annotation.SxtStudent");
        Annotation[] annotations=clazz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }
        SxtTable st = (SxtTable) clazz.getAnnotation(SxtTable.class);
        System.out.println(st.value());
        Field f = clazz.getDeclaredField("studentName");
        SxtField sxtField = f.getAnnotation(SxtField.class);
        System.out.println(sxtField.columnName()+"--"+sxtField.type()+"--"+sxtField.length());

    }
}
