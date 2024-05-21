package main.java.ca.nl.cna.java2.assignment.A01.Shape;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        int num = 0;
        for (Shape shape : shapes) {
            try {
                shape = ShapeGenerator.generateShape();
                shapes[num] = shape;
                num++;
            } catch (Exception e) {
                System.out.println(e);
            }
        }


        for (Shape currentShape : shapes) {
            if (currentShape != null){
                System.out.println(currentShape.getDescription());
            }

        }
    }
}