package edu.uchicago.gerber._07streams.E13_20;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args){
        Payment payment = new Payment();
        System.out.println("Please input an integer indicating the number that you'll pay: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("Here are the ways that you can make payment: ");
        payment.showResult(num);

    }


}
