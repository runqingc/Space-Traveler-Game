package P10_5;


import javax.swing.*;

public class HouseViewer {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setSize(700, 400);

        frame.setTitle("My Lovely House");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent component = new HouseComponent();
        frame.add(component);
        frame.setVisible(true);



    }


}
