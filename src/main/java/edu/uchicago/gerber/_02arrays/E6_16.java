package edu.uchicago.gerber._02arrays;

import java.util.Arrays;
import java.util.Scanner;



public class E6_16 {


    public static void main(String[] args){
        System.out.println("Please input 10 random number with maximum 20. ");
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }
        int maximum = Arrays.stream(array).max().getAsInt();
        for(int i=0; i<maximum; ++i){
            for(int j = 0; j < 10; ++j){
                if(array[j]>=maximum-i){
                    System.out.print('*');
                }else{
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

        // test:
        // 3 16 2 17 12 8 9 20 5 9

    }

}
