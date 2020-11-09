package com.dys.java8.function;

import java.util.function.BiFunction;

public class Demo07 {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Double> biFunction = (a, b) -> (double)(a * b);
        BiFunction<Integer, Integer, Double> biFunction1 = (a, b) -> {return (double)(a * b);};
        BiFunction<Integer, Integer, Double> biFunction2 = new Calcu();

        cal(10, 20, biFunction);
        cal(10, 20, biFunction1);
        cal(10, 20, biFunction2);

        BiFunction<Integer, Integer, Double> biFunction3 = new BiFunction<Integer, Integer, Double>() {
            @Override
            public Double apply(Integer a, Integer b) {
                return (double)(a * b);
            }
        };
        cal(10, 20, biFunction3);

        System.out.println(biFunction.apply(10, 20));

    }

    private static void cal(int a, int b, BiFunction<Integer, Integer, Double> biFunction) {
        System.out.println(biFunction.apply(a, b));
    }
}

class Calcu implements BiFunction<Integer, Integer, Double> {

    @Override
    public Double apply(Integer a, Integer b) {
        return (double)(a * b);
    }
}
