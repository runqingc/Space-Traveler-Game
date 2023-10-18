package edu.uchicago.gerber._04interfaces.P9_1;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args){

        Clock localClock = new Clock();

        System.out.println(localClock.getTime());

        Scanner in = new Scanner(System.in);

        System.out.println("You can specify an offset to create a new world clock. Enter an offset: ");

        int offset = in.nextInt();

        WorldClock worldClock = new WorldClock(offset);

        System.out.println(worldClock.getTime());

    }


}
