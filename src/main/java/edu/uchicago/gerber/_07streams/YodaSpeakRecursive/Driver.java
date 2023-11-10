package edu.uchicago.gerber._07streams.YodaSpeakRecursive;


import java.util.Scanner;

public class Driver {

    public static void main(String[] args){

        System.out.println("Please input a string");
        Scanner scanner = new Scanner(System.in);
        String original = scanner.nextLine();
        YodaSpeakRecursive yodaSpeak = new YodaSpeakRecursive();
        System.out.println("The reverse of the string is: ");
        System.out.println(yodaSpeak.reverseString(original));

    }

}
