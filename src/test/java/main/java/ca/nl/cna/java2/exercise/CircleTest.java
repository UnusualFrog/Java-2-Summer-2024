package main.java.ca.nl.cna.java2.exercise;

import main.java.ca.nl.cna.java2.exercise.ex1_shapes.Circle;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @org.junit.jupiter.api.Test
    void area() {
        Circle circle = new Circle(1.0);
        assertEquals(circle.getRadius() * Math.PI, circle.Area());
    }
}