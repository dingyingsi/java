package com.dys.java8.text;

public class Demo21 {
    public static void main(String[] args) {

        String query = "select `username` from user\n" +
                "where `id` = 129\n" +
                "order by `id`, `username` desc;\n";

        String query1 = """
            select `username` from user
            where `id` = 129
            order by `id`, `username` desc;
        """;

        System.out.println(query);
        System.out.println(query1);


    }
}
