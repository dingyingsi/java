package com.dys.java8.function;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Func {

    public static void main(String[] args) {
        JButton jButton = new JButton("按钮");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮事件");
            }
        });

        jButton.addActionListener(event -> {
            System.out.println("按钮事件lamba");
        });


        jButton.addActionListener((ActionEvent event) -> {
            System.out.println("按钮事件lamba");
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("abc");
            }
        });

        new Thread(() -> {
            System.out.println("efg");
        });

        List<String> list = Arrays.asList("ding", "ying", "si");
        Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String a, String b) {
                        return b.compareTo(a);
                    }
                }
        );
        for (String string : list) {
            System.out.println(string);
        }

        List<String> list1 = Arrays.asList("ding", "ying", "si");
        Collections.sort(list1, (a, b) -> b.compareTo(a)
        );
        for (String string : list1) {
            System.out.println(string);
        }

        int a = 10, b = 20;
        cal(a, b, (Calculator<Integer>) (a1, b1) -> a1 + b1);
        cal(a, b, (Calculator<Integer>) (a1, b1) -> {
            return a1 + b1;
        });

        cal(a, b, new Calculator<Integer>() {

            @Override
            public Integer calculate(Integer a, Integer b) {
                return a + b;
            }
        });

    }

    private static void cal(int a, int b, Calculator<Integer> calculator) {
        System.out.println(calculator.calculate(a, b));
    }


    // 函数接口
    interface Calculator<T> {
        T calculate(T a, T b);
    }
}

