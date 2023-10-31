package edu.uchicago.gerber._06design.E12_4;

import java.util.Random;

public class Question3 extends Question{
    public Question3() {
        super();
        System.out.println("lv3");
        Random rand = new Random();
        number1 = rand.nextInt(9);
        number2 = -rand.nextInt(number1);
    }

    @Override
    public void showQuestion(){
        String sign = "-";
        System.out.println("Question:" + number1 + sign + -number2 + "= ? ");
        System.out.print("Enter you answer here: ");

    }
}
