package com.dys.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class T03 {
    public static void main(String[] args) throws Exception {

        String path = "User";
        Class<User> clazz = (Class<User>) Class.forName(path);
        User u = clazz.newInstance();
        System.out.println(u);
        Constructor<User> c = clazz.getDeclaredConstructor(int.class, int.class, String.class);
        User u2 = c.newInstance(1001, 18, "丁应思");
        System.out.println(u2.getUname());
        User u3 = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("setUname", String.class);
        method.invoke(u3, "abc");   //u3.setUname("abc");
        System.out.println(u3.getUname());
        User u4 = clazz.newInstance();
        Field f = clazz.getDeclaredField("uname");
        f.setAccessible(true);
        f.set(u4, "abc");
        System.out.println(u4.getUname());
        System.out.println(f.get(u4));


    }

    // 获取泛型参数
    public void test01(Map<String,User> map, List<User> list){
        System.out.println("Demo04.test01()");
    }
    public Map<Integer,User> test02(){
        System.out.println("Demo04.test02()");
        return null;
    }
}
