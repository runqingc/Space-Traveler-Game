package edu.uchicago.gerber._04interfaces.E9_8;

public class BasicAccount extends BankAccount{

    public void withdraw(double amount){
        if(amount>this.getBalance()){
            System.out.println("The money is not enough. ");
        }else{
            super.withdraw(amount);
        }
    }

}
