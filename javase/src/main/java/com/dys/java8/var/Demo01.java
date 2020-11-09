package com.dys.java8.var;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Demo01 {
    public static void main(String[] args) {
        public class Demo17 {
            public static void main(String[] args) throws Exception {
                var strVar = "java81113";
                System.out.println(strVar instanceof String);

                var varL = 10L;

                var varF = true;

                var listVar = new ArrayList<String>();
                System.out.println(listVar instanceof ArrayList);

                var streamVar = Stream.of("aa", "bb", "cc");
                System.out.println(streamVar instanceof Stream);
                if(varF){
                    System.out.println("true");
                }

                for (var i = 0; i < 10; i++) {
                    System.out.println(i);
                }
                try (var input = new FileInputStream("abc.txt")) {
                }
            }
        }

    }
}
