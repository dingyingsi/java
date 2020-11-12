package com.dys.reflection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class T05 {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<A> as = new ArrayList<>();
        as.add(new A("11"));
        as.add(new A("12"));
        System.out.println(readValue(mapper.writeValueAsString(as),A.class));
        Class<A> aClass = A.class;
        System.out.println(t1(mapper.writeValueAsString(as),aClass));
    }
    public static <T> T readValue(String str,Class<T> V) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T t = (T) mapper.readValue(str, new TypeReference<List<T>>() {});
        return t;
    }
    public static <T> T t1(String str,T V)throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return (T)mapper.readValue(str,new TypeReference<List<T>>(){});
    }
    public static <T,V> void t(T V)throws IOException{
        System.out.println(V);
    }
    public static class A{
        String a;
        public A(String a) {
            this.a = a;
        }
    }

}
