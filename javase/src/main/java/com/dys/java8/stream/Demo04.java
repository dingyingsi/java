package com.dys.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo04 {

    public static void main(String args[]) {

        List<User> users = Arrays.asList(
                new User(10, "Jack"),
                new User(20, "Tom"),
                new User(30, "Cate")
        );
        //过滤年龄小于20的对象
        List<User> collect = users.parallelStream().filter(user -> user.getAge() < 20).collect(Collectors.toList());
        System.out.println(collect);

        //过滤年龄小于20的对象的name属性
        List<String> names = users.parallelStream().filter(user -> user.getAge() < 20).map(User::getName).collect(Collectors.toList());
        System.out.println(names);


        // foreach()不是一个循环，不是设计为可以用break以及continue来中止的操作。
        List<String> list = Arrays.asList("123", "45634", "7892", "abch", "sdfhrthj", "mvkd");
        list.stream().forEach(e ->{
            if(e.length() >= 5){
                return;
            }
            System.out.println(e);
        });


    }

}
