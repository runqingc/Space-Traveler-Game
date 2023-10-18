package edu.uchicago.gerber._04interfaces.E9_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        ArrayList<SodaCan> canList = new ArrayList<>();;
        boolean continueRunning = true;

        while (continueRunning) {

            System.out.println("\nSelect an option:");
            System.out.println("1: Measure a soda can");
            System.out.println("2: Quit");

            int choice = in.nextInt();
            in.nextLine();  // Consume newline
            if(choice==1){
                System.out.print("Enter radius: ");
                double r = in.nextDouble();
                System.out.print("Enter height: ");
                double h = in.nextDouble();
                SodaCan tempSodaCan = new SodaCan(r,h);
                canList.add(tempSodaCan);
                System.out.println("surface area: " + canList.get(canList.size()-1).getMeasure());
            }else{
                continueRunning = false;
            }


        }


    }
}
