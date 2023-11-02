package edu.uchicago.gerber._06design.P12_1;

public class Item {

    String name;

    Double price;

    Integer count;

    public Item() {
    }

    public Item(String name, Double price, Integer count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return name +
                "  price=" + price +
                ", number left=" + count;
    }
}
