package main.java.ca.nl.cna.java2.exercise.ex1_shapes;

public class Circle extends Shape {

    double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double Area(){
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public String getDescription(){
        return "Circle with radius: " + this.radius + " and area:" + this.Area();
    }

    public double getRadius(){
        return this.radius;
    }

    public void setRadius(double newRadius){
        this.radius = newRadius;
    }
}
