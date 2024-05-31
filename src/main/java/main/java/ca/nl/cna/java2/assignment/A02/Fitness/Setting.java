package main.java.ca.nl.cna.java2.assignment.A02.Fitness;

import java.util.Date;

public class Setting {
    private String name;
    private double height;
    private double weight;
    private Date birthday;
    private double power;

    public Setting() {
        this.name = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.birthday = new Date();
        this.power = 0.0;
    }

    public Setting(String name, double height, double weight, Date birthday, double power){
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
