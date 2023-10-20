package edu.uchicago.gerber._04interfaces.E9_6;

public abstract class Appointment {

    String description;

    public Appointment() {
    }

    public Appointment(String description) {
        this.description = description;
    }

    public abstract boolean occursOn(int year, int month, int day);
}
