package main.java.ca.nl.cna.java2.assignment.A01.Shape;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) throws InvalidShapeParameterException {
        this.radius = radius;

        if (this.radius < 0){
            throw new InvalidShapeParameterException();
        }
    }

    public Double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    @Override
    public Double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getDescription() {
        return String.format("Circle with radius %.1f has a area of %.2f", radius, area());
    }
}
