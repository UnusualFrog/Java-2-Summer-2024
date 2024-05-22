package main.java.ca.nl.cna.java2.assignment.A01.ex11_16;

//3. 11.16 (Catching Exceptions with Superclasses) Use inheritance to create
//an exception superclass (called ExceptionA) and exception subclasses
//ExceptionB and ExceptionC, where ExceptionB inherits from
//ExceptionA and ExceptionC inherits from ExceptionB. Write a
//program to demonstrate that the catch block for type ExceptionA
//catches exceptions of types ExceptionB and ExceptionC.

public class ExceptionA extends Exception {

    public ExceptionA(String msg){
        super(msg);
    }
}
