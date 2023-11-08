package edu.uchicago.gerber._07streams.E13_20;

public class Payment {


    int[] array;

    final int[]cash = {100, 20, 5, 1};


    public void cal(int sum, int max_cash){

        if(max_cash==3){
            array[max_cash] = sum;
            System.out.println("$100*"+array[0]+"; $20*"+array[1]+"; $5*"+array[2]+"; $1*"+array[3]);
            return;
        }

        int n = sum/cash[max_cash];

        for(int i=n; i>=0; --i){

            array[max_cash] = i;
            cal(sum-i*cash[max_cash], max_cash+1);

        }

    }

    public void showResult(int num){

        array = new int[]{0, 0, 0, 0};
        cal(num, 0);

    }

}
