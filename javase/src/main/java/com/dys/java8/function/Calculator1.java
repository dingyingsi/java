package com.dys.java8.function;

import java.util.function.Consumer;

/**
 * 1.常见的方法引用有 User::new, System.out::println
 */
public class Calculator1 {
 
    private static void print(String message) {
        System.out.println(message);
    }
 
    private void sayHello(String world) {
        System.out.println(world);
    }
 
    public static void  main(String...args) {
        Consumer<String> print = Calculator1::print;
        print.accept("message");
 
    }
}
