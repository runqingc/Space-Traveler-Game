package edu.uchicago.gerber._02arrays;

import java.util.ArrayList;
import java.util.Random;

public class E6_1 {


    public static void main(String[] args){
        ArrayList<Integer> randomArray = new ArrayList<>(10);
        Random rand = new Random();
        for(int i=0; i<10; ++i){
            randomArray.add(i, rand.nextInt(100));
        }


        System.out.println("Elements at an even index.");
        for(int i=0; i<10; i+=2){
            System.out.println(randomArray.get(i));
        }
        System.out.println();

        System.out.println("Every even element.");
        for(int i=0; i<10; ++i){
            if(randomArray.get(i)%2==0){
                System.out.println(randomArray.get(i));
            }
        }
        System.out.println();

        System.out.println("All elements in reverse order");
        for(int i=9; i>=0; --i){
            System.out.println(randomArray.get(i));
        }
        System.out.println();

        System.out.println("First and last element: ");
        System.out.println(randomArray.get(0));
        System.out.println(randomArray.get(9));
    }
}
