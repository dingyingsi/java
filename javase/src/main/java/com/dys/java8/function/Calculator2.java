package com.dys.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 　　1.闭包体“name -> name.indexOf("J") != -1”， 作为参数传递给predicate中， predicate.test(name), 将name作为参数传递给闭包体。
 * 　　2.具体其他函数接口参照java.uitl.function包。
 */
public class Calculator2 {
 
    public static void main(String args[]) {
        List<String> names = Arrays.asList("Jack", "Jimmy", "Tom", "Cate");
 
        find(names, name -> name.indexOf("J") != -1); //找出名称中包含J的
 
        find(names, name -> name.indexOf("C") != -1); //找出名称中包含C的
 
    }
 
    private static void find(List<String> names, Predicate<String> predicate) {
 
        for(String name : names) {
            if (predicate.test(name)) {
                System.out.println(name);
            }
        }
 
    }
}
