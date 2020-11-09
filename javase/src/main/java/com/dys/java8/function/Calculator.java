package com.dys.java8.function;

/**
 * 注意：
 * 　　1.Lambada表达式也称为闭包，类似于C语言中的函数指针；
 * 　　2.Lambada表达式，形参>=2个时，要用括号；一个参数时括号可以省略；
 * 　　3.Lambada表达式, 如果闭包体只一个语句时，可以不用return, 默认返回该语句的结果；如果闭包体语句>=2时，返回值要用return;
 * 　　4.Lambada表达式, 不能在闭包体内修改外部非final标记的变量；
 * 　　5.Lambada表达式, 在闭包体内局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义）；
 * 　　6.上面代码，如果接口中有两个访求，就不能用函数式接口赋值方式；
 */
public class Calculator {
    public static void  main(String...args) {
        Calculate plus = (int a, int b) -> a + b;
        Calculate substract = (a, b) -> a - b;
        Calculate multiply = (int a, int b) -> {return a * b;};
        Calculate divide = (a, b) -> {return a / b;};

        Printer printer = result -> System.out.println(result);

        int a = 20, b = 10;

        int result = plus.calculate(a, b);
        printer.print(result);

        result = substract.calculate(a, b);
        printer.print(result);

        result = multiply.calculate(a, b);
        printer.print(result);
        result = divide.calculate(a, b);
        printer.print(result);

    }

    interface Calculate {
        int calculate(int a, int b);
    }

    interface Printer {
        void print(int result);
    }
}
