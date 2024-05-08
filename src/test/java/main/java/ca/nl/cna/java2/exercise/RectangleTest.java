package main.java.ca.nl.cna.java2.exercise;

import main.java.ca.nl.cna.java2.exercise.ex1_shapes.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void area() {
        Rectangle rectangle = new Rectangle(2.0, 6.0);
        assertEquals(rectangle.Area(), rectangle.getWidth() * rectangle.getLength());
    }
}