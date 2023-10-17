package edu.uchicago.gerber._04interfaces.E9_8;

public class SavingsAccount extends BankAccount{

    private double interestRate;
    private double minBalance;

    /**
     Constructs a savings account with a zero balance.
     */
    public SavingsAccount() {
        super();
    }

    /**
     Sets the interest rate for this account.
     @param rate the monthly interest rate in percent
     */
    public void setInterestRate(double rate) {
        this.interestRate = rate;
    }


    public void withdraw(double amount) {
        super.withdraw(amount);
        if(this.getBalance()<minBalance){
            minBalance = this.getBalance();
        }
    }
    public void monthEnd() {
        double interest = minBalance * interestRate / 100;
        deposit(interest);
        minBalance = getBalance();
    }
}
