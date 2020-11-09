package com.dys.java8.stream;

import java.util.Optional;

public class Demo05 {

    public static void main(String[] args) {

        User user = new User(10, "tom");
        User user1 = null;

        Optional<User> opt1 = Optional.of(user);
        //Optional<User> opt = Optional.of(user1);


        Optional<User> opt3 = Optional.ofNullable(user);
        if (opt3.isPresent()) {
            User user3 = opt3.get();
            System.out.println(user3.id);
        }

        Optional<User> opt2 = Optional.ofNullable(user1);
        if (opt2.isPresent()) {
            User user2 = opt2.get();
            System.out.println(user2.id);
        }

        User user3 = Optional.ofNullable(user1).orElse(user);
        System.out.println(user3.id);

        int result = Optional.ofNullable(user1).map(param -> param.id).orElse(9);
        System.out.println(result);

    }

    private static class User {
        public Integer id;
        public String name;
        public User(Integer id, String name) { this.id = id; this.name = name; }
    }

}
