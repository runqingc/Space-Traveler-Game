package edu.uchicago.gerber._04interfaces.E9_8;

public class CheckingAccount extends BankAccount{

    private int withdrawals;

    /**
     9.4 Polymorphism 461
     Constructs a checking account with a zero balance.
     */
    public CheckingAccount() {
        super();
    }

    public void withdraw(double amount)
    {
        final int FREE_WITHDRAWALS = 3;
        final int WITHDRAWAL_FEE = 1;
        super.withdraw(amount);
        withdrawals++;
        if (withdrawals > FREE_WITHDRAWALS)
        {
            super.withdraw(WITHDRAWAL_FEE);
        }
    }

    public void monthEnd()
    {
        withdrawals = 0;
    }
}
