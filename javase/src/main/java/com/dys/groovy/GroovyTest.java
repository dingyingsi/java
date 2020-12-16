package com.dys.groovy;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyTest {
    public static void main(String[] args) {
        GroovyShell groovyShell = new GroovyShell();
        String array = "['a', 'b', 'c']";
        String exp = "datasource.user_${" + array + "}";
        Script parse = groovyShell.parse("\"" + exp + "\"");
        Object run = parse.run();
        System.out.println(run);
    }
}
