package com.dys.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Demo08 {
    public static void main(String[] args) {
        Consumer<Integer> consumer = a -> System.out.println("打印信息: " + a);
        Consumer<Integer> consumer1 = a -> {System.out.println("打印信息: " + a);};
        Consumer<Integer> consumer2 = new Calcul();
        Consumer<Integer> consumer3 = new Consumer<Integer>() {
            @Override
            public void accept(Integer a) {
                System.out.println("打印信息: " + a);
            }
        };

        consumer.accept(10);
        consumer1.accept(10);
        consumer2.accept(10);
        consumer3.accept(10);

        List<String> strs = Arrays.asList("a", "b", "c");
        strs.forEach(str -> {
            System.out.println("打印信息: " + str);
        });

        Consumer<String> stringConsumer = str -> System.out.println("打印信息: " + str);
        strs.forEach(stringConsumer);

    }
}

class Calcul implements Consumer<Integer> {

    @Override
    public void accept(Integer a) {
        System.out.println("打印信息: " + a);
    }
}
