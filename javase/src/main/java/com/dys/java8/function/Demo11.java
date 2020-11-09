package com.dys.java8.function;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Demo11 {
    public static void main(String[] args) {
        Function<String, Integer> parsetIntFunction = Integer::parseInt;
        Integer value = parsetIntFunction.apply("123");
        System.out.println(value);

        String str = "java is a computer programming language";
        Function<Integer, String> substringFunction = str::substring;
        String apply = substringFunction.apply(3);
        System.out.println(apply);

        Supplier<String> strSupplier = str::toUpperCase;
        String s = strSupplier.get();
        System.out.println(s);

        IntSupplier intSupplier = str::length;
        int asInt = intSupplier.getAsInt();
        System.out.println(asInt);

        BiFunction<Integer, String, User1> aNew = User1::new;
        User1 user1 = aNew.apply(10, "abc");
        System.out.println(user1);

    }
}

class User1 {
    public Integer id;
    public String name;
    public User1(Integer id, String name) {this.id = id; this.name = name;}

    @Override
    public String toString() { return "User{" +  "id=" + id + ", name='" + name + '\'' + '}'; }
}
