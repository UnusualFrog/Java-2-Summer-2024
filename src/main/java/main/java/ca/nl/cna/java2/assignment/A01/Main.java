package main.java.ca.nl.cna.java2.assignment.A01;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        int num = 0;
        for (Shape shape : shapes) {
            try {
                shape = ShapeGenerator.generateShape();
            } catch (Exception e) {
                System.out.println(e);
            }

            shapes[num] = shape;
            num++;
            System.out.println(shape.getDescription());
        }
    }
}