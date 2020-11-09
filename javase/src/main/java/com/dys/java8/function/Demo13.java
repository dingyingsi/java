package com.dys.java8.function;

import java.util.List;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) throws Exception {
        List<String> list = List.of("dog", "cat", "elephant", "", "giraffe", "").stream().takeWhile(obj -> !obj.isEmpty()).collect(Collectors.toList());
        System.out.println(list);

        List<String> list1 = List.of("dog", "cat", "elephant", "", "giraffe", "").stream().dropWhile(obj->!obj.isEmpty()).collect(Collectors.toList());
        System.out.println(list1);
    }
}
