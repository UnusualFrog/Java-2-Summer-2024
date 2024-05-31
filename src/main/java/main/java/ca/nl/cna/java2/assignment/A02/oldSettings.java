package main.java.ca.nl.cna.java2.assignment.A02;

public class oldSettings {
    private String name;
    private double height;
    private double weight;
    private String birthday;
    private double power;

    public oldSettings() {
        this.name = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.birthday = "01/01/1970";
        this.power = 0.0;
    }

    public oldSettings(String name, double height, double weight, String birthday, double power){
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
