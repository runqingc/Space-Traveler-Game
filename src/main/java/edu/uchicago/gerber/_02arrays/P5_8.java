package edu.uchicago.gerber._02arrays;

import java.util.ArrayList;

public class P5_8 {

    public static boolean isLeapYear(int year){
        if(year<0){
            return false;
        }else if(year%400==0){
            return true;
        }else{
            return year % 100 != 0 && year % 4 == 0;
        }
    }

    public static void main(String[] args){
        // Test cases
        assert !isLeapYear(1583);
        assert isLeapYear(2000);
        assert !isLeapYear(1900);
        assert isLeapYear(1996);
        assert isLeapYear(2012);
        assert !isLeapYear(2023);
        assert isLeapYear(2024);
        assert isLeapYear(1604);
        assert isLeapYear(1728);
        assert isLeapYear(1820);
        assert isLeapYear(1968);
        assert !isLeapYear(1700);
        assert !isLeapYear(1805);
        assert !isLeapYear(1903);
        assert !isLeapYear(2019);
        assert isLeapYear(-4);
        assert !isLeapYear(-5);

        System.out.println("All tests passed!");

    }

}
