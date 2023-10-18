package edu.uchicago.gerber._04interfaces.E9_13;

import java.util.Scanner;

import static java.lang.System.in;

public class Driver {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the position and size of the rectangular, format: ");
        System.out.println("x_pos y_pos height weight");
        int x_pos = in.nextInt();
        int y_pos = in.nextInt();
        int h = in.nextInt();
        int w = in.nextInt();

        BetterRectangle betterRectangle = new BetterRectangle(x_pos, y_pos, h, w);
        System.out.println("The perimeter of your rectangle is:" + betterRectangle.getPerimeter());
        System.out.println("The area of your rectangle is:" + betterRectangle.getArea());


    }
}
