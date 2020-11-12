package com.dys.reflection;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class T08 {
    public static void main(String[] args) throws Exception {
        String str = "public class Hi {public static void main(String[] args){System.out.println(\"HaHa,sxt!\");}}";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "c:/myjava/HelloWorld.java");
        System.out.println(result == 0 ? "success" : "fail");
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("java -cp  c:/myjava    HelloWorld");
        InputStream in = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String info = "";
        while((info=reader.readLine())!=null){
            System.out.println(info);
        }
        URL[] urls = new URL[] { new URL("file:/" + "C:/myjava/") };
        URLClassLoader loader = new URLClassLoader(urls);
        Class c = loader.loadClass("HelloWorld");
        Method m = c.getMethod("main", String[].class);
        m.invoke(null, (Object) new String[] {});
        m.invoke(null, (Object)new String[]{});
        m.invoke(null,"aa","bb");

    }
}
