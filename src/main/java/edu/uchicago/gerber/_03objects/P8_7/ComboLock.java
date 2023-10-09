package edu.uchicago.gerber._03objects.P8_7;

import java.util.ArrayList;

public class ComboLock {
    private int secret1;
    private int secret2;
    private int secret3;

    private ArrayList<Integer> attempts;

    public ComboLock(int secret1, int secret2, int secret3) {
        this.secret1 = secret1;
        this.secret2 = secret2;
        this.secret3 = secret3;
        this.attempts = new ArrayList<>();
        attempts.ensureCapacity(100);
    }

    public void turnLeft(int ticks){
        attempts.add(-ticks);
    }

    public void turnRight(int ticks){
        attempts.add(ticks);
    }

    public void reset(){
        attempts.clear();
        attempts.add(0);
        attempts.add(0);
        attempts.add(0);
    }

    public boolean open() {
        boolean flag = attempts.get(0)==secret1 && -attempts.get(1)==secret2 && attempts.get(2)==secret3;
        this.reset();
        return flag;
    }


}
