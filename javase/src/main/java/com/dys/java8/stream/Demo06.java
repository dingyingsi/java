package com.dys.java8.stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Demo06 {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        scriptEngine.put("a", 100);
        scriptEngine.put("b", 0.25);

        Object eval = scriptEngine.eval("a * (1 + b)");
        System.out.println(eval);
    }
}
