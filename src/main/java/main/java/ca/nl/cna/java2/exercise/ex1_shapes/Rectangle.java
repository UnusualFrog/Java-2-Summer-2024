package main.java.ca.nl.cna.java2.exercise.ex1_shapes;

public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double Area(){
        return this.length * this.width;
    }

    @Override
    public String getDescription(){
        return "Rectangle with length:" + this.length + " Width: " + this.width + " and Area:" + this.Area();
    }

    public double getLength(){
        return this.length;
    }

    public void setLength( double newLength){
        this.length = newLength;
    }

    public double getWidth(){
        return this.width;
    }

    public void setWidth(double newWidth){
        this.width = newWidth;
    }
}
