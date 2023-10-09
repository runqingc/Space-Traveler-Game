package edu.uchicago.gerber._03objects.P8_5;

public class SodaCan {

    double radius;
    double height;

    public SodaCan() {
        this.radius = 0;
        this.height = 0;
    }

    public SodaCan(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSurfaceArea(){
        return 2*3.1415*radius*radius+2*3.1415*height;
    }

    public double getVolume(){
        return 3.1415*radius*radius*height;
    }


}
