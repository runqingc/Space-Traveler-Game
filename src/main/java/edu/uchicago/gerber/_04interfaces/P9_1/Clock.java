package edu.uchicago.gerber._04interfaces.P9_1;

public class Clock {

    public int getHour(){
        java.time.LocalTime now = java.time.LocalTime.now();
        return now.getHour();  // gets the current hour
    }

    public int getMinute(){
        java.time.LocalTime now = java.time.LocalTime.now();
        return now.getMinute();  // gets the current minute
    }

    public String getTime(){
        return "The time in yout current location is: " +
                getHour() + ": " + getMinute();

    }

}
