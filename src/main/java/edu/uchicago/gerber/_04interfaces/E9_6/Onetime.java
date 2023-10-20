package edu.uchicago.gerber._04interfaces.E9_6;

public class Onetime extends Appointment{

    int year;
    int month;
    int day;

    public Onetime(String description, int year, int month, int day) {
        super(description);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return this.year==year && this.month==month && this.day==day;
    }
}
