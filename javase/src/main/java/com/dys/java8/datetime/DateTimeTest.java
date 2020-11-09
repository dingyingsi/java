package com.dys.java8.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfWeek());

        LocalDate plusYears = now.plusYears(1);
        System.out.println(now.getYear());
        System.out.println(plusYears.getYear());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowString = dateTimeFormatter.format(now);
        System.out.println(nowString);

        LocalDateTime nowdDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        nowString = dateTimeFormatter.format(nowdDateTime);
        System.out.println(nowString);

        LocalDate of = LocalDate.of(2090, 9, 21);
        System.out.println(of);

        LocalDateTime ldt = LocalDateTime.of(2090, 9, 21, 18, 29, 50);
        System.out.println(ldt);


        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        LocalDateTime changeDate = LocalDateTime.of(2090, 9, 21, 18, 29, 50);
        System.out.println(changeDate);
        Duration duration = Duration.between(localDateTime1, changeDate);

        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());

    }
}
