package edu.uchicago.gerber._07streams.E13_4;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args){

        Binary binary = new Binary();
        System.out.println("Please input an integer: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("The binary string of this integer is: ");
        System.out.println(binary.convertBinary(num));
    }
}
