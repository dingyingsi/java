package com.dys.charset;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) throws Exception {

        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();

        stringCharsetSortedMap.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });

    }
}
