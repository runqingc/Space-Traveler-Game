package edu.uchicago.gerber._03objects.P8_1;

public class Driver {

    public static void main(String[] args){

        Microwave microwave = new Microwave();
        microwave.start();
        microwave.increaseTime();
        microwave.setLevel(2);
        microwave.increaseTime();
        microwave.start();
        microwave.reset();
        microwave.start();
    }
}
