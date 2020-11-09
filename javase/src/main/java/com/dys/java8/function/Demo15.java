package com.dys.java8.function;

public class Demo15 {
    public static void main(String[] args) {

        PrivateMethodInterface.d();

    }
}

interface PrivateMethodInterface {
    private void a() {
        System.out.println("private interface method");
    }

    default void b() {
        a();
    }

    void c();

    static void d() {
        System.out.println("d");
    }
}
