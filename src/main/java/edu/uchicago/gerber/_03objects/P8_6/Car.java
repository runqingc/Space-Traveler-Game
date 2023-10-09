package edu.uchicago.gerber._03objects.P8_6;

public class Car {

    double efficiency;

    double gasLevel;

    public Car() {
    }

    public Car(double efficiency) {
        this.efficiency = efficiency;
        this.gasLevel = 0;
    }

    public double getGasLevel() {
        return gasLevel;
    }

    public void addGas(double amount){
        this.gasLevel+=amount;
    }

    public void drive(double distance){
        if(distance>gasLevel*efficiency){
            System.out.println("not enough oil!");
        }else{
            this.gasLevel-= distance/efficiency;
        }
    }
}
