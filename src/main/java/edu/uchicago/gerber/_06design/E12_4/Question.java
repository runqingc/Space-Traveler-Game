package edu.uchicago.gerber._06design.E12_4;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Question {

    int number1;

    int number2;

    int tries;

    private static final int MAX_NUM_TRIES=2;

    public Question() {
        this.tries = 0;
    }

    public void showQuestion(){
        String sign = (number2>=0)?("+"):("");
        System.out.println("Question:" + number1 + sign + number2 + "= ? ");
        System.out.print("Enter you answer here: ");

    }

    public Boolean testAnswer(){
        Scanner scanner = new Scanner(System.in);
        int result;
        while(tries<=MAX_NUM_TRIES){
            try {
                result = scanner.nextInt();
                if(result==number1+number2){
                    System.out.print("Great job! Correct answer. ");
                    return true;
                }else{
                    ++tries;
                    System.out.print("Oops! Wrong answer. ");
                    if(tries==1){
                        System.out.println("Please try again.");
                    }else{
                        int ans = number1+number2;
                        System.out.println("The correct answer is: " + ans);
                        return false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers.");
                ++tries;
                scanner.nextLine(); // to consume the invalid token
            }
        }
        return false;
    }

}
