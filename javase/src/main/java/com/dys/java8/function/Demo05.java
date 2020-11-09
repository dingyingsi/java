package com.dys.java8.function;

public class Demo05 {
    public static void main(String[] args) {
        invoke(10, 20, (a, b) -> (double) (a + b));
        invoke(10, 20, new Cal<Integer, Double>() {
            @Override
            public Double cal(Integer a, Integer b) {
                return (double) (a + b);
            }
        });
    }

    private static void invoke(int a, int b, Cal<Integer, Double> cal) {
        double result = cal.cal(a, b);
        System.out.println(result);
    }


}

// 函数接口
@FunctionalInterface
interface Cal<T, R> {
    R cal(T a, T b);
}
