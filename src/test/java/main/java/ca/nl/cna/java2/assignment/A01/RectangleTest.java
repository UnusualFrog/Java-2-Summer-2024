package main.java.ca.nl.cna.java2.assignment.A01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void createRectangle(){
        assertThrows(InvalidShapeParameterException.class, () -> {
            Rectangle rect = new Rectangle(-20.0, -10.1);
        });
    }

    @Test
    void getLength() {
    }

    @Test
    void setLength() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void setWidth() {
    }

    @Test
    void area() {
    }

    @Test
    void getDescription() {
    }
}