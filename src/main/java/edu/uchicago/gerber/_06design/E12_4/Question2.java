package edu.uchicago.gerber._06design.E12_4;

import java.util.Random;

public class Question2 extends Question{

    public Question2() {
        super();
        System.out.println("lv2");
        Random rand = new Random();
        number1 = rand.nextInt(9);
        number2 = rand.nextInt(9);
    }
}
