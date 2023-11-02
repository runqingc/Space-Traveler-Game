package edu.uchicago.gerber._06design.P12_1;


import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        VendingMachine vendingMachine= new VendingMachine();
        Scanner scanner = new Scanner(System.in);
        while(true) {

            System.out.println(vendingMachine.showGoods());

            // ask user whether to quit
            System.out.println("Please enter the name of item you want. Enter + to add goods. Enter - to remove all money. Enter # to quit. ");
            String name = scanner.nextLine();
            if (name.equals("#")) {
                System.out.println("Bye! Have a nice day. ");
                break;
            } else if (name.equals("+")) {
                System.out.println("Enter the name of goods you want to insert. ");
                String addName = scanner.nextLine();
                int checkName = vendingMachine.checkGoodsName(addName);
                if(checkName==VendingMachine.INVALID_GOODS_NAME_ERROR){
                    System.out.println("Sorry. INVALID GOODS NAME. Try a new one. ");
                }else{
                    System.out.println("Enter the sum you want to add. ");
                    int sum = scanner.nextInt();
                    vendingMachine.addGoods(addName, sum);
                    System.out.println("Success. ");
                }


            } else if (name.equals("-")) {
                System.out.println("Here's all the money in the machine. \n" +
                        vendingMachine.money.printCoin());

            } else {
                int checkName = vendingMachine.checkGoodsName(name);
                if (checkName == VendingMachine.OUT_OF_STOCK_ERROR) {
                    System.out.println("Sorry. The item you selected is out of stock. Try a new one. ");

                } else if (checkName == VendingMachine.GOOD_NAME_VALID) {
                    System.out.println("You have select: " + name + ". ");
                    System.out.println("Please enter coins.\n" +
                            "Enter 1 to insert a 1 cent coin, \n" +
                            "enter 5 to insert a 5 cent coin, \n" +
                            "enter 10 to insert a 10 cent coin, \n" +
                            "enter 25 to insert a quarter coin, \n" +
                            "enter 50 to insert a quarter coin, \n" +
                            "enter 100 to insert a dollar coin.\n" +
                            "enter other number to quit");
                    Coins enteredCoins = new Coins();
                    int thisCoin = scanner.nextInt();
                    while(true){
                        if(thisCoin==1){
                            ++enteredCoins.num1;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else if(thisCoin==5){
                            ++enteredCoins.num5;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else if(thisCoin==10){
                            ++enteredCoins.num10;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else if(thisCoin==25){
                            ++enteredCoins.num25;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else if(thisCoin==50){
                            ++enteredCoins.num50;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else if(thisCoin==100){
                            ++enteredCoins.num100;
                            System.out.println("You have entered a cent. " +
                                    "Total number you have entered: $"+enteredCoins.totalAmount());
                        }else{
                            break;
                        }
                        thisCoin = scanner.nextInt();
                    }
                    int res = vendingMachine.sellGoods(name, enteredCoins);
                    if(res==VendingMachine.NOT_ENOUGH_MONEY_ERROR){
                        System.out.println("Sorry, the money you have entered is not enough. Please try again. ");
                    }else if(res==VendingMachine.PURCHARSE_SUCCESSFUL){
                        System.out.println("Purchase Successful. Here's your return money. ");
                        System.out.println(enteredCoins.printCoin());
                    }



                } else if (checkName == VendingMachine.INVALID_GOODS_NAME_ERROR) {
                    System.out.println("Sorry. We don't have this item. Try a new one. ");

                }
            }
            String tmp = scanner.nextLine();

        }
    }


}
