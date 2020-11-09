package com.dys.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Calculator1 {
 
    public static void main(String args[]) {
 
        List<User> users = Arrays.asList(
                new User(10, "Jack"),
                new User(20, "Tom"),
                new User(30, "Cate"),
                new User(30, "Andy")
        );
        Map<Integer, String> collect = users.parallelStream().collect(Collectors.toMap(new Function<User, Integer>() {
            @Override
            public Integer apply(User t) {
                return t.getAge();
            }
        }, new Function<User, String>() {
            @Override
            public String apply(User t) {
                return t.getName();
            }
        }, new BinaryOperator<String>() {
            @Override
            public String apply(String value1, String value2) {
                return value1 + value2;
            }
        }));
        System.out.println(collect);
    }
 
    private static class User {
        private Integer age;
        private String name;
        public User(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
