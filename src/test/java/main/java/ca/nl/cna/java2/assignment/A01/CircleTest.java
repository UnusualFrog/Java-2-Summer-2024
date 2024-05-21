package main.java.ca.nl.cna.java2.assignment.A01;

import main.java.ca.nl.cna.java2.assignment.A01.Shape.Circle;
import main.java.ca.nl.cna.java2.assignment.A01.Shape.InvalidShapeParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void createInvalidCircle(){
        assertThrows(InvalidShapeParameterException.class, () -> {
           Circle circle = new Circle(-20.0);
        });
    }

    @Test
    void createValidCircle(){
        Circle circle = null;
        try {
            circle = new Circle(5.5);
        }catch (InvalidShapeParameterException e) {
            System.out.println(e);
        }

        assertNotNull(circle);

    }
}