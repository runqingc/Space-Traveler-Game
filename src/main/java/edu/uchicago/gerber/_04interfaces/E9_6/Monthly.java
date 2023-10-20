package edu.uchicago.gerber._04interfaces.E9_6;

public class Monthly extends Appointment{

    int day;

    public Monthly(String description, int day) {
        super(description);
        this.day = day;
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return this.day == day;
    }
}
