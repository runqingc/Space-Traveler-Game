package edu.uchicago.gerber._04interfaces.P9_1;

public class WorldClock extends Clock{

    private int offset;

    public WorldClock(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getTime(){
        int hour = offset+super.getHour();
        if(hour>=24){
            hour-=24;
        }else if(hour<0){
            hour+=24;
        }
        int minute = super.getMinute();
        return "The time in this location is: " +
                hour + ": " + minute;
    }
}
