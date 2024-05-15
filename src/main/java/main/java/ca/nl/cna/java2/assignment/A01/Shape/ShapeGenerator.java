package main.java.ca.nl.cna.java2.assignment.A01.Shape;

import java.util.Random;

public class ShapeGenerator {
    public ShapeGenerator(){
    }

    public static Shape generateShape(){
        int coinTossResult = new Random().nextInt(2);
        double radius = new Random().nextInt(-100,100);
        double length = new Random().nextInt(-100,100);
        double width = new Random().nextInt(-100,100);
        if (coinTossResult == 0){

            Circle circle = null;
            try {
                circle =  new Circle(radius);
            }catch (InvalidShapeParameterException e) {
                System.err.println(e);
            }
            return circle;
        }
        else{
            Rectangle rect = null;
            try {
                rect =  new Rectangle(length,width);
            }catch (InvalidShapeParameterException e) {
                System.err.println(e);
            }
            return rect;
        }
    }
}
