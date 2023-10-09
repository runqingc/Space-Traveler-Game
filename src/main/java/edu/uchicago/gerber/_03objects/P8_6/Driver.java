package edu.uchicago.gerber._03objects.P8_6;

public class Driver {

    public static void main(String[] args){
        Car myHybrid = new Car(50); // 50 miles per gallon
        System.out.println(myHybrid.getGasLevel()); // Print fuel remaining
        myHybrid.addGas(20); // Tank 20 gallons
        System.out.println(myHybrid.getGasLevel()); // Print fuel remaining
        myHybrid.drive(100); // Drive 100 miles
        System.out.println(myHybrid.getGasLevel()); // Print fuel remaining
    }
}
