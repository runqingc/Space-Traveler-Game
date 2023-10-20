package edu.uchicago.gerber._04interfaces.E9_6;

public class Daily extends Appointment{

    public Daily(String description){
        super(description);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return true;
    }
}
