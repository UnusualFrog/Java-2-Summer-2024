package main.java.ca.nl.cna.java2.test.test1_practice;

import java.io.IOException;

public class ExceptionTest extends Exception{
    public ExceptionTest() throws Exception {
        method1();
    }

    public void method1 () throws Exception {
        try {
            method2();
        } catch (Exception e) {
            throw new Exception("EEE", e);
        }
    }

    public void method2 () throws IOException {
        throw new IndexOutOfBoundsException("AAAA!");
    }

    public static void main(String[] args) {
        try {
            ExceptionTest exceptionTest = new ExceptionTest();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
