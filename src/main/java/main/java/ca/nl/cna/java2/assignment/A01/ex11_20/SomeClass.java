package main.java.ca.nl.cna.java2.assignment.A01.ex11_20;

//11.20 (Rethrowing Exceptions) Write a program that illustrates
//rethrowing an exception. Define methods someMethod and
//someMethod2. Method someMethod2 should initially throw an
//exception. Method someMethod should call someMethod2, catch the
//exception and rethrow it. Call someMethod from method main, and
//catch the rethrown exception. Print the stack trace of this exception.

public class SomeClass {
    public void someMethod() throws Exception {
        try {
            someMethod2();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void someMethod2() throws Exception {
        throw new Exception("someMethod2");
    }

    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        try {
            someClass.someMethod();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}