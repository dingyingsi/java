package com.dys.java8.function;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class Demo12 {
    public static void main(String[] args) {
        List<String> strs = Arrays.asList("abcdefg", "hijklmn", "opq");
        List<String> collect = strs.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect1 = strs.stream().filter(str -> str.length() > 3).collect(Collectors.toList());
        System.out.println(collect1);

        List<String> list = Arrays.asList("a", "bc", "cde", "defg");
        List<String> resultList = list.stream().sorted().collect(Collectors.toList());
        System.out.println(resultList);

        List<String> collect2 = list.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println(collect2);

        List<String> collect22 = list.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        System.out.println(collect22);

        List<String> collect3 = list.stream().sorted(Comparator.comparing(obj -> obj.length())).collect(Collectors.toList());
        System.out.println(collect3);

        List<String> collect4 = list.stream().sorted(Comparator.comparing(obj -> obj.length(), Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println(collect4);

        List<String> collect5 =  list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        System.out.println(collect5);


        List<String> collect6 = list.stream().sorted(Comparator.comparing(String::length).reversed()).limit(3).collect(Collectors.toList());
        System.out.println(collect6);

        boolean flag = list.stream().allMatch(obj -> obj.length() > 1);
        System.out.println(flag);

        flag = list.stream().anyMatch(obj -> obj.length() > 3);
        System.out.println(flag);


        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 9);
        Integer max = ints.stream().max(Integer::compare).get();
        System.out.println(max);

        Integer min = ints.stream().min(Integer::compare).get();
        System.out.println(min);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().forEach(System.out::println);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums.parallelStream().forEach(System.out::println);


        for(int i=0;i<10;i++) {
          //  List list1 = new ArrayList();
            List list1 = new CopyOnWriteArrayList();
            IntStream.range(0, 50).parallel().forEach(list1::add);
            System.out.println("size = " + list.size());
        }

        int value = Stream.of(1, 2, 3, 4, 5).reduce((item1, item2) -> item1 + item2).get();
        System.out.println(value);

        int value1 = Stream.of(1, 2, 3, 4,5).reduce(50, (sum, item) -> sum + item);
        System.out.println(value1);

        int value2 = Stream.of(1, 2, 3, 5, 4, 7, 6, 10).reduce( (item1, item2) -> item1 >  item2 ? item1 : item2 ).get();
        System.out.println(value2);

        nums.forEach(System.out::println);

        List<Integer> collect7 = nums.stream().filter(num -> num > 3).collect(Collectors.toList());
        System.out.println(collect7);

        List<Integer> collect8 = nums.stream().filter(num -> num > 3).collect(new Collector<Integer, List<Integer>, List<Integer>>() {

            @Override
            public Supplier<List<Integer>> supplier() {
                return (Supplier<List<Integer>>) ArrayList::new;
            }

            @Override
            public BiConsumer<List<Integer>, Integer> accumulator() {
                return List::add;
            }

            @Override
            public BinaryOperator<List<Integer>> combiner() {
                 BinaryOperator<List<Integer>> binaryOperator = (left, right) -> { left.addAll(right); return left; };
                 return binaryOperator;
            }

            @Override
            public Function<List<Integer>, List<Integer>> finisher() {
                Function<List<Integer>, List<Integer>> a = param -> param;
                return a;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
            }
        });
        System.out.println(collect8);

        List<String> strss = Arrays.asList("a", "b", "c", "d");
        String collect9 = strss.stream().filter(param -> param.length() > 0).collect(Collectors.joining());
        System.out.println(collect9);

        String collect10 = strss.stream().filter(param -> param.length() > 0).collect(Collectors.joining(","));
        System.out.println(collect10);

        String collect11 = strss.stream().filter(param -> param.length() > 0).collect(Collectors.joining(",", "[", "]"));
        System.out.println(collect11);

        List<String> lista = Arrays.asList("abdefd", "javaseabc", "chinese","good","thanks");
        Map<Boolean, List<String>> result = lista.stream().collect(partitioningBy(obj -> obj.length() > 4));
        System.out.println(result);

        List<User2> user2s = Arrays.asList(new User2("abc", 23), new User2("efg", 24), new User2("hij", 23),new User2("lmn", 23));
        Map<Integer, List<User2>> listMap = user2s.stream().collect(Collectors.groupingBy(obj -> obj.age));
        System.out.println(listMap);

        Map<Integer, Long> listMap2 = user2s.stream().collect(Collectors.groupingBy(user2 -> user2.age, Collectors.counting()));
        System.out.println(listMap2);


        IntSummaryStatistics summaryStatistics = user2s.stream().collect(Collectors.summarizingInt(user2 -> user2.age));
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getSum());
    }
}

class  User2 {
    public String name;
    public Integer age;
    public User2(String name, Integer age) {this.name = name; this.age = age;}
}
