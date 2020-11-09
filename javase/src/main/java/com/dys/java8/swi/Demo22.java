package com.dys.java8.swi;

public class Demo22 {

    public static void main(String[] args) {
        t01(11);
        t01(1);
        t01(2);
        t01(100);
    }

    public static void t01(int i){
        switch(i){
            case 0 -> {
                System.out.println("zero");
                System.out.println("00000000000");
            }
            case 1,11,111 -> System.out.println("1, 11, 111");
            case 2 -> System.out.println("two");
            default -> System.out.println("default");
        }
    }
}
