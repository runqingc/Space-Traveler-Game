package edu.uchicago.gerber._04interfaces.E9_17;

public class SodaCan implements Measurable{

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


    @Override
    public double getMeasure() {
        return Math.PI*radius*radius+2*Math.PI*radius*height;
    }
}
