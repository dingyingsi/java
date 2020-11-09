package com.dys.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 * Map<Long, List<Long>> menuButtonIds = baseAdminFunctionMenuButtons.stream()
 *         .collect(Collectors.groupingBy(BaseAdminFunctionMenuButton::getMenuId, Collectors.mapping(BaseAdminFunctionMenuButton::getButtonId, Collectors.toList())));
 *
 *
 */

public class Calculator {
 
    public static void main(String args[]) {
 
        List<User> users = Arrays.asList(
                new User(10, "Jack"),
                new User(20, "Tom"),
                new User(30, "Cate"),
                new User(30, "Andy")
        );
        Map<Integer, String> collect = users.parallelStream().collect(Collectors.toMap(User::getAge, User::getName, (value1, value2) -> value1 + value2));
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
