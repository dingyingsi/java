package com.dys.java8.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo07 {

    public static void main(String[] args) {
        Map<String, Map<String, User>> multiMap = new HashMap<>();

        User user1 = new User(1L, "abc");
        Map<String, User> map1 = new HashMap<>();
        map1.put("a", user1);

        User user2 = new User(2L, "abcd");
        Map<String, User> map2 = new HashMap<>();
        map1.put("a", user2);

        User user3 = new User(1L, "abc");
        Map<String, User> map3 = new HashMap<>();
        map1.put("b", user3);

        multiMap.put("aa1", map1);
        multiMap.put("aa2", map2);
        multiMap.put("aa3", map3);


        Map<String, List<User>> collect = multiMap.values().stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        System.out.println(collect);

    }

    private static class User {
        private Long id;
        private String name;

        public User() {}
        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
