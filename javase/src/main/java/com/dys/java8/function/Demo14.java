package com.dys.java8.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo14 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");

        list = Collections.unmodifiableList(list);

        list.add("b");
        System.out.println(list);
    }
}