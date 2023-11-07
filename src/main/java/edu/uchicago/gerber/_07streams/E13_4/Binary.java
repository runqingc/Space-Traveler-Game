package edu.uchicago.gerber._07streams.E13_4;

public class Binary {

    String s;

    public void recursive(int n){
        s = n%2 +s;
        n/=2;
        if(n>0) recursive(n);
    }

    public String convertBinary(int n){
        s = "";
        recursive(n);
        return s;
    }
}
