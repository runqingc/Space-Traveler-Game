package edu.uchicago.gerber._04interfaces.E9_8;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args){

        // Step 1: Create an array of BankAccount
        BankAccount[] accounts = new BankAccount[15];

        // Step 2: Initialize the array with checking and savings accounts
        for (int i = 0; i < 5; i++) {
            accounts[i] = new BasicAccount();
        }
        for (int i = 5; i < 10; i++) {
            accounts[i] = new SavingsAccount();
            ((SavingsAccount) accounts[i]).setInterestRate(1.0); // Setting interest rate to 1% for simplicity
        }
        for (int i = 10; i < 15; i++) {
            accounts[i] = new CheckingAccount();
        }

        Scanner in = new Scanner(System.in);
        boolean done = false;
        while (!done)
        {
            System.out.print("D)eposit W)ithdraw M)onth end Q)uit: ");
            String input = in.next();
            if (input.equals("D") || input.equals("W")) // Deposit or withdrawal
            {System.out.print("Enter account number and amount: ");
                int num = in.nextInt();
                double amount = in.nextDouble();
                if (input.equals("D")) { accounts[num].deposit(amount); }
                else { accounts[num].withdraw(amount); }
                System.out.println("Balance: " + accounts[num].getBalance());
            }
            else if (input.equals("M")) // Month end processing
            {
                for (int n = 0; n < accounts.length; n++)
                {
                    accounts[n].monthEnd();
                    System.out.println(n + " " + accounts[n].getBalance());
                }
            }
            else if (input.equals("Q"))
            {
                done = true;
            }
        }

    }
}
