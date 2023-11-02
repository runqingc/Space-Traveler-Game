package edu.uchicago.gerber._06design.P12_1;

import java.util.ArrayList;

public class VendingMachine {

    Coins money;

    ArrayList<Item> stock;


    public static int PURCHARSE_SUCCESSFUL=0;

    public static int ADD_SUCCESSFUL=0;
    public static int GOOD_NAME_VALID=0;

    public static int INVALID_GOODS_NAME_ERROR=1;
    public static int OUT_OF_STOCK_ERROR=2;
    public static int NOT_ENOUGH_MONEY_ERROR=3;


    // provide some default items and money so that we will be able to have money changed
    public VendingMachine() {

        money = new Coins(20, 20, 20, 20, 20, 20);

        stock = new ArrayList<>();
        stock.add(new Item("Soda", 0.25, 10));
        stock.add(new Item("Lemonade", 2.50, 10));
        stock.add(new Item("Apple Juice", 3.00, 10));
        stock.add(new Item("Chocolate Milk", 5.50, 10));
        stock.add(new Item("Fruit Punch", 4.50, 10));
        stock.add(new Item("Coca Cola", 2.20, 10));
    }

    // show all the goods to a user;
    public String showGoods(){
        StringBuilder ret = new StringBuilder("Welcome to vending machine. Here are all the goods: \n");
        for(Item item: stock){
            ret.append(item.toString()).append('\n');
        }

        return ret.toString();
    }

    public int addGoods(String goodName, int sum){
        if(checkGoodsName(goodName)==INVALID_GOODS_NAME_ERROR){
            return INVALID_GOODS_NAME_ERROR;
        }
        for (Item item : stock) {
            if (item.name.equals(goodName)) {
                item.count += sum;
            }
        }
        return ADD_SUCCESSFUL;
    }

    public int checkGoodsName(String goodName){
        Item itemToSell = null;
        // Find the item by name
        for (Item item : stock) {
            if (item.name.equals(goodName) && item.count > 0) {
                itemToSell = item;
                break;
            }
        }
        if (itemToSell == null) {
            return INVALID_GOODS_NAME_ERROR; // Item not found or out of stock
        }

        return GOOD_NAME_VALID;
    }



    int sellGoods(String goodName, Coins coins){

        Item itemToSell = null;
        // Find the item by name
        for (Item item : stock) {
            if (item.name.equals(goodName) && item.count > 0) {
                itemToSell = item;
                break;
            }
        }
        if (itemToSell == null) {
            return INVALID_GOODS_NAME_ERROR; // Item not found or out of stock
        }
        if(coins.totalAmount()< itemToSell.price){
            return NOT_ENOUGH_MONEY_ERROR;
        }
        itemToSell.count--;
        money.num1 += coins.num1;
        money.num5 += coins.num5;
        money.num10 += coins.num10;
        money.num25 += coins.num25;
        money.num50 += coins.num50;
        money.num100 += coins.num100;

        // calculate change
        Coins changeInCoins = coins.calculateChange(coins.totalAmount()-itemToSell.price);

        // Assuming I have appropriate setters in the Coins class or direct access to fields
        coins.num1 = changeInCoins.num1;
        coins.num5 = changeInCoins.num5;
        coins.num10 = changeInCoins.num10;
        coins.num25 = changeInCoins.num25;
        coins.num50 = changeInCoins.num50;
        coins.num100 = changeInCoins.num100;

        // decrease number of coins in the machine
        money.num1 -= changeInCoins.num1;
        money.num5 -= changeInCoins.num5;
        money.num10 -= changeInCoins.num10;
        money.num25 -= changeInCoins.num25;
        money.num50 -= changeInCoins.num50;
        money.num100 -= changeInCoins.num100;


        return PURCHARSE_SUCCESSFUL;
    }



}
