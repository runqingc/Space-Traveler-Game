package edu.uchicago.gerber._02arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class E6_12 {

    public static void main(String[] args){
        ArrayList<Integer> randomArray = new ArrayList<>(20);
        Random rand = new Random();
        System.out.println("Before sort: ");
        for(int i=0; i<20; ++i){
            randomArray.add(i, rand.nextInt(100));
            System.out.print(randomArray.get(i)+" ");
        }
        System.out.println();
        System.out.println("After sort: ");
        Collections.sort(randomArray);
        for(int i=0; i<20; ++i){
            System.out.print(randomArray.get(i)+" ");
        }
    }
}
