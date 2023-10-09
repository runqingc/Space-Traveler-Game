package edu.uchicago.gerber._03objects.P8_7;

public class Driver {

    public static void main(String[] args){

        ComboLock comboLock = new ComboLock(12, 5, 37);
        comboLock.turnRight(13);
        comboLock.turnLeft(6);
        comboLock.turnRight(20);
        if(comboLock.open()){
            System.out.println("The lock is opened. ");
        }else{
            System.out.println("The lock is not opened. ");
        }
        comboLock.turnRight(12);
        comboLock.turnLeft(5);
        comboLock.turnRight(37);
        if(comboLock.open()){
            System.out.println("The lock is opened. ");
        }else{
            System.out.println("The lock is not opened. ");
        }
    }
}
