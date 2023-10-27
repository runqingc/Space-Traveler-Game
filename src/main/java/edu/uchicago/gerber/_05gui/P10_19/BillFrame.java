package P10_19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BillFrame extends JFrame {
    private static final int ITEM_COUNT = 10;
    private JLabel[] itemNameLabels;
    private JLabel[] itemPriceLabels;
    private JTextField[] quantityTextFields;
    private JButton[] increaseButtons;
    private JButton[] decreaseButtons;

    private ArrayList<String> menu;
    private ArrayList<Double> menuPrice;



    private JLabel totalPrice;
    double price;




    private JTextField tipRate;
    private JLabel suggestTip;



    public BillFrame() {
        setTitle("Restaurant Bill");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new ArrayList<>(Arrays.asList("apple", "pear", "orange", "milk", "bread", "egg", "pork", "beef", "salmon", "shrimp"));
        menuPrice = new ArrayList<>(Arrays.asList(1.12,4.32,3.54,5.55, 6.00,2.32, 0.98, 9.87, 4.56, 12.23));
        price = 0;
        totalPrice = new JLabel("Total price: $" + price);
        tipRate = new JTextField("0.1");
        suggestTip = new JLabel("Total tips: $" + price*Double.parseDouble(tipRate.getText()));

        JPanel itemsPanel = new JPanel(new GridLayout(ITEM_COUNT, 5));

        itemNameLabels = new JLabel[ITEM_COUNT];
        itemPriceLabels = new JLabel[ITEM_COUNT];
        quantityTextFields = new JTextField[ITEM_COUNT];
        increaseButtons = new JButton[ITEM_COUNT];
        decreaseButtons = new JButton[ITEM_COUNT];

        for (int i = 0; i < ITEM_COUNT; i++) {
            itemNameLabels[i] = new JLabel(menu.get(i));
            itemPriceLabels[i] = new JLabel("$" + menuPrice.get(i));
            quantityTextFields[i] = new JTextField("0");
            increaseButtons[i] = new JButton("+");
            decreaseButtons[i] = new JButton("-");

            int index = i;  // For use in the inner class
            int finalI = i;
            increaseButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int qty = Integer.parseInt(quantityTextFields[index].getText());
                    qty++;
                    quantityTextFields[index].setText(String.valueOf(qty));
                    price += menuPrice.get(finalI);
                    totalPrice.setText("Total price: $" + price);
                    suggestTip.setText("Total tips: $" + price*Double.parseDouble(tipRate.getText()));
                }
            });

            decreaseButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int qty = Integer.parseInt(quantityTextFields[index].getText());
                    if (qty > 0) {
                        qty--;
                        quantityTextFields[index].setText(String.valueOf(qty));
                        price -= menuPrice.get(finalI);
                        totalPrice.setText("Total price: $" + price);
                        suggestTip.setText("Total tips: $" + price*Double.parseDouble(tipRate.getText()));
                    }
                }
            });

            itemsPanel.add(itemNameLabels[i]);
            itemsPanel.add(itemPriceLabels[i]);
            itemsPanel.add(decreaseButtons[i]);
            itemsPanel.add(quantityTextFields[i]);
            itemsPanel.add(increaseButtons[i]);
        }
        // Create bottom panel with a layout to hold multiple components
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1)); // 3 rows, 1 column

        bottomPanel.add(totalPrice);
        bottomPanel.add(new JLabel("Tip Rate:"));
        bottomPanel.add(tipRate);
        bottomPanel.add(suggestTip);

        add(itemsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }





}
