package edu.uchicago.gerber._06design.P12_1;

public class Coins {

    // suppose our vending machine only accepts 1, 5, 10, 25, 50, 100 cents coin.

    int num1;

    int num5;

    int num10;

    int num25;

    int num50;

    int num100;

    public Coins() {
        num1 = 0;
        num5 = 0;
        num10 = 0;
        num25 = 0;
        num50 = 0;
        num100 = 0;
    }

    public Coins(int num1, int num5, int num10, int num25, int num50, int num100) {
        this.num1 = num1;
        this.num5 = num5;
        this.num10 = num10;
        this.num25 = num25;
        this.num50 = num50;
        this.num100 = num100;
    }

    // calculate total amount
    public double totalAmount(){
        return num1 * 0.01 + num5 * 0.05 + num10 * 0.10 +
                num25 * 0.25 + num50 * 0.50 + num100 * 1.0;
    }

    public Coins calculateChange(double change) {
        Coins changeInCoins = new Coins();
        int c = (int) (change*100);

        // Calculate change for each coin type (this is a greedy approach and may not work with all coin sets)
        changeInCoins.num100 = c / 100;
        c %= 100;
        changeInCoins.num50 = c / 50;
        c %= 50;
        changeInCoins.num25 = c / 25;
        c %= 25;
        changeInCoins.num10 = c / 10;
        c %= 10;
        changeInCoins.num5 = c / 5;
        c %= 5;
        changeInCoins.num1 = c;

        return changeInCoins;
    }

    public String printCoin(){
        StringBuilder sb = new StringBuilder();
        if(num1>0) sb.append("One cent *").append(num1).append('\n');
        if(num5>0) sb.append("Five cents *").append(num5).append('\n');
        if(num10>0) sb.append("Ten cent *").append(num10).append('\n');
        if(num25>0) sb.append("Quarter *").append(num25).append('\n');
        if(num50>0) sb.append("Fifty cents *").append(num50).append('\n');
        if(num100>0) sb.append("One hundred cents *").append(num100).append('\n');
        sb.append("Total number: ").append(totalAmount()).append('\n');
        return sb.toString();
    }


}
