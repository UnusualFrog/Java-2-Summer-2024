package main.java.ca.nl.cna.java2.exercise.ex1_shapes;

public class Test {


    public static void main(String[] args) {
        Circle circle = new Circle(1.0);
        System.out.println(circle.getDescription());

        Rectangle rectangle = new Rectangle(1.0, 2.0);
        System.out.println(rectangle.getWidth());
        rectangle.setWidth(4.0);
        System.out.println(rectangle.getDescription());

        ShapeGenerator shapeGenerator = new ShapeGenerator();
        Shape myShape = shapeGenerator.generateRandomShape();
        System.out.println(myShape.getDescription());

        Shape[] shapes = new Shape[5];

        int num = 0;
        for(Shape shape : shapes){
            shape = shapeGenerator.generateRandomShape();
            shapes[num] = shape;
            num++;
            System.out.println(shape.getDescription());
        }
    }
}
