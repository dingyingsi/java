package com.dys.java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo10 {
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("aaa", "bbbb", "ccccc");
        Stream<String> stringStream = strs.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 3;
            }
        });
        List<String> collect = stringStream.collect(Collectors.toList());
        System.out.println(collect);

        Predicate<String> predicate = param -> {
            return param.length() > 3;
        };
        Predicate<String> predicate1 = param -> param.length() > 3;
        List<String> collect1 = strs.stream().filter(predicate).collect(Collectors.toList());
        List<String> collect2 = strs.stream().filter(predicate1).collect(Collectors.toList());

        System.out.println(collect1);
        System.out.println(collect2);
    }
}
