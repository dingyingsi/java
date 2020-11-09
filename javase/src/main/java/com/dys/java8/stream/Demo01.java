package com.dys.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo01 {
    public static void main(String args[]) {
        List<User> users = Arrays.asList(
                new User(10, "Jack"),
                new User(20, "Tom"),
                new User(30, "Cate"),
                new User(30, "Cate")
        );
        Map<String, List<User>> collect = users.parallelStream().collect(Collectors.groupingBy(user -> user.age + user.name, Collectors.toList()));
        System.out.println(collect);
    }



}
