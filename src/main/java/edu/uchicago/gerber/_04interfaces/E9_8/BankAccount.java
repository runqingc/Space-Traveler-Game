package edu.uchicago.gerber._04interfaces.E9_8;

public class BankAccount {

    private double balance;

    public BankAccount() {
        balance = 0;
    }

    /**
     Makes a deposit into this account.
     @param amount the amount of the deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     Makes a withdrawal from this account, or charges a penalty if
     sufficient funds are not available.
     @param amount the amount of the withdrawal
     */
    public void withdraw(double amount) {
        balance -= amount;
    }

    /**
     Carries out the end of month processing that is appropriate
     for this account.
     */
    public void monthEnd() {

    }

    /**
     Gets the current balance of this bank account.
     @return the current balance
     */
    public double getBalance() {
        return balance;
    }


}
