package main.java.ca.nl.cna.java2.exercise.ex1_shapes;

import java.util.Random;

public class ShapeGenerator {
    public Shape generateRandomShape(){
        Random random = new Random();

        if (random.nextInt(2) == 1){
            return new Circle(2.0);
        }
        else{
            return new Rectangle(2.0, 4.0);
        }
    }
}
