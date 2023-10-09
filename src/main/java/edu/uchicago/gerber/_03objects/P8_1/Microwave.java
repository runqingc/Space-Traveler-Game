package edu.uchicago.gerber._03objects.P8_1;

public class Microwave {

    private int time;

    private int level;

    public Microwave(){
        time=0;
        level=0;
    }

    public Microwave(int time, int level) {
        this.time = time;
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void start(){
        System.out.println("the remaining time is: "+ time + " seconds, at level of "+ level);
    }

    public void reset(){
        time=0;
        level=0;
    }

    public void increaseTime(){
        time+=30;
    }

}
