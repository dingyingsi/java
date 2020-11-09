package com.dys.java8.function;

import java.util.function.Function;

public class Demo06 {
    public static void main(String[] args) {

        Function<Integer, Double> function = new Calc();
        cal(10, function);

        Function<Integer, Double> function1 = a -> (double)(a * 100);
        Function<Integer, Double> function2 = a -> {return (double)(a * 100);};
        Function<Integer, Double> function3 = new Function<Integer, Double>() {
            @Override
            public Double apply(Integer a) {
                return (double) (a * 100);
            }
        };

        cal(10, function1);
        cal(10, function2);
        cal(10, function3);

        System.out.println(function1.apply(10));

    }

    private static void cal(int a, Function<Integer, Double> function) {
        double result = function.apply(a);
        System.out.println(result);
    }
}

class Calc implements Function<Integer, Double> {

    @Override
    public Double apply(Integer a) {

        return (double)(a * 100);
    }
}
