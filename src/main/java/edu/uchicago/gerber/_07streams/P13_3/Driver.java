package edu.uchicago.gerber._07streams.P13_3;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args){


        System.out.println("Please in put a number string indicating phone number. \n" +
                "Eg : 762842, 562843 or 2633465282\n" +
                "Please do not input 1 because there is no match for 1 on the phone pad!");
        Phone phone = new Phone();
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = scanner.nextLine();
        System.out.println("Here are all the combination: ");
//        phone.convertPhoneNumber(phoneNumber);
        phone.convertEntry(phoneNumber);

    }

}
