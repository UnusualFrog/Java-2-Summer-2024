package main.java.ca.nl.cna.java2.assignment.A01.ex11_20;

//11.20 (Rethrowing Exceptions) Write a program that illustrates
//rethrowing an exception. Define methods someMethod and
//someMethod2. Method someMethod2 should initially throw an
//exception. Method someMethod should call someMethod2, catch the
//exception and rethrow it. Call someMethod from method main, and
//catch the rethrown exception. Print the stack trace of this exception.

public class SomeClass {
    public static void someMethod() throws Exception {
        try {
            someMethod2();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void someMethod2() throws Exception {
        throw new Exception("someMethod2");
    }

    public static void main(String[] args) {
        try {
            SomeClass.someMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
