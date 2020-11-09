package com.dys.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo02 {

    public static void main(String args[]) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        boolean b = integers.parallelStream().anyMatch(integer -> integer == 2);

        System.out.println(b);


        b = integers.parallelStream().anyMatch(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer == 2;
            }
        });

        System.out.println(b);

        Set<Integer> collect = integers.parallelStream().limit(3).collect(Collectors.toSet());
        System.out.println(collect);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());


    }


}
