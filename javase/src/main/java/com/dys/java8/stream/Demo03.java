package com.dys.java8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Demo03 {

    public static void main(String args[]) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "AAA");
        hashMap.put("b", "BBB");
        hashMap.put("c", "CCC");

        List<String> collect = hashMap.values().parallelStream().collect(ArrayList::new, (values, value) -> values.add(value), ArrayList::addAll);

        System.out.println("object = " + collect);

        collect = hashMap.values().parallelStream().collect(new Supplier<List<String>>() {
            @Override
            public List<String> get() {
                return new ArrayList<String>();
            }
        }, new BiConsumer<List<String>, String>() {
            @Override
            public void accept(List<String> strs, String s) {
                strs.add(s);
            }
        }, new BiConsumer<List<String>, List<String>>() {
            @Override
            public void accept(List<String> o, List<String> o2) {
                o.addAll(o2);
            }
        });

        System.out.println("object = " + collect);


    }


}
