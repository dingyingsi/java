package com.dys.java8.function;

/**
 * 　1.接口中的默认方法在接口中默认实现，在接口中修改该方法，可以避免所有实现类都要修改实现方法的情况；
 * 　　2.调用父接口中的方法是要用"父接口.super.方法"的形式。
 */
public class Calculator3 {
 
    public static void main(String args[]) {
        Calculator.Three three = new Calculator().new Three();
        three.print();
    }
 
    class Three implements One, Two {
 
        @Override
        public void print() {
            System.out.println("Three");
            One.super.print();
            Two.super.print();
        }
    }
 
    interface One {
        default void print() {
            System.out.println("one");
        }
    }
 
    interface Two {
        default void print() {
            System.out.println("two");
        }
    }
}
