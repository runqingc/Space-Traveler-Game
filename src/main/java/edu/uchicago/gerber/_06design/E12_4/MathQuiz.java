package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;

public class MathQuiz {

    int level;

    int score;

    public MathQuiz(){
        this.level = 1;
        this.score = 0;
    }


    public void start(){

        while(true){
            Question q;
            if(level == 1){
                q = new Question1();
            }else if(level ==2){
                q = new Question2();
            }else{
                q = new Question3();
            }
            q.showQuestion();
            if(q.testAnswer()){
                ++score;
            }
            System.out.println("current score: "+ score);
            if(score>=5 && level<=2){
                score=0;
                level++;
                System.out.println("Congratulation! You have get into level" + level);
            }

            System.out.println("Enter # to quit. Enter other characters to continue. ");
            Scanner scanner= new Scanner(System.in);
            String ans = scanner.nextLine();
            if(ans.equals("#")){
                break;
            }
        }
    }
}
