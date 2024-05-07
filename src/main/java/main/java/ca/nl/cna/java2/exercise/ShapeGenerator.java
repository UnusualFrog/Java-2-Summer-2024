package main.java.ca.nl.cna.java2.exercise;

import java.util.Random;

public class ShapeGenerator {
    public Shape generateRandomShape(){
        Random random = new Random();
        Shape shape;

        if (random.nextInt(2) == 1){
            shape = new Circle(2.0);
        }
        else{
            shape = new Rectangle(2.0, 4.0);
        }

        return shape;
    }
}
