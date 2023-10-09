package edu.uchicago.gerber._03objects.P8_5;

public class Driver {

    public static void main(String[] args){
        SodaCan sodaCan = new SodaCan(2, 5);
        System.out.println("the surface area of the soda can is: "+ sodaCan.getSurfaceArea());
        System.out.println("the volume of the soda can is: "+ sodaCan.getVolume());
    }

}
