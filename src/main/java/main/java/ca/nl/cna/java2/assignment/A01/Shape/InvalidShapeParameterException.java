package main.java.ca.nl.cna.java2.assignment.A01.Shape;

public class InvalidShapeParameterException extends Exception{
    private static final String msg = "Shape parameter is not valid!";

    public InvalidShapeParameterException(){
        super(msg);
    }

}

