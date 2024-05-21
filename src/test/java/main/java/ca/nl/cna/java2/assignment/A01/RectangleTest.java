package main.java.ca.nl.cna.java2.assignment.A01;

import main.java.ca.nl.cna.java2.assignment.A01.Shape.InvalidShapeParameterException;
import main.java.ca.nl.cna.java2.assignment.A01.Shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void createInvalidRectangle(){
        // First negative
        assertThrows(InvalidShapeParameterException.class, () -> {
            Rectangle rect = new Rectangle(-20.0, 10.1);
        });

        // Last negative
        assertThrows(InvalidShapeParameterException.class, () -> {
            Rectangle rect = new Rectangle(20.0, -10.1);
        });

        // Both negative
        assertThrows(InvalidShapeParameterException.class, () -> {
            Rectangle rect = new Rectangle(-20.0, -10.1);
        });
    }

    @Test
    void createValidRectangle() {
        Rectangle rectangle = null;

        try{
            rectangle = new Rectangle(10.0, 5.2);
        }
        catch (InvalidShapeParameterException e) {
            System.err.println(e);
        }

        assertNotNull(rectangle);
    }

}