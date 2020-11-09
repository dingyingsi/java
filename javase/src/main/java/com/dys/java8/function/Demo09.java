package com.dys.java8.function;

import java.util.function.Supplier;

public class Demo09 {
    public static void main(String[] args) {

        User user = studentFactory();
        System.out.println(user);

        Supplier<User> supplierUser = () -> {return new User(20, "efg");};
        User user1 = supplierUser.get();
        System.out.println(user1);

    }

    private static User studentFactory() {
        Supplier<User> supplier = new Su();
        return supplier.get();
    }
}

class Su implements Supplier<User> {

    @Override
    public User get() {
        return new User(10, "abc");
    }
}

class User {
    public Integer id;
    public String name;
    public User(Integer id, String name) {this.id = id; this.name = name;}

    @Override
    public String toString() { return "User{" +  "id=" + id + ", name='" + name + '\'' + '}'; }
}
