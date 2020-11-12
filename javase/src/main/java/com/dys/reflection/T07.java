package com.dys.reflection;

import java.lang.reflect.Method;

public class T07 {
    public static void test01(){
        User u = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            u.getUname();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("测试时间"+(endTime-startTime)+"ms");
    }
    public static void test02() throws Exception{
        User u = new User();
        Class clazz = u.getClass();
        Method m = clazz.getDeclaredMethod("getUname", (Class<?>[]) null);
//  m.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            m.invoke(u, (Object[]) null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("测试时间"+(endTime-startTime)+"ms");
    }
    public static void test03() throws Exception{
        User u = new User();
        Class clazz = u.getClass();
        Method m = clazz.getDeclaredMethod("getUname", (Class<?>[]) null);
        m.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            m.invoke(u, (Object[]) null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("测试时间"+(endTime-startTime)+"ms");
    }
    public static void main(String[] args) throws Exception {
        test01();
        test02();
        test03();
    }

}
