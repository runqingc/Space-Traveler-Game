package edu.uchicago.gerber._06design.E12_4;

import java.util.Random;

public class Question1 extends Question{

    public Question1() {

        super();
        System.out.println("lv1");
        Random rand = new Random();
        number1 = rand.nextInt(10);
        number2 = rand.nextInt(10-number1);
    }
}
