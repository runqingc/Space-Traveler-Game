package P10_2;


import javax.swing.*;

public class BullEyeViewer {
    public static void main(String[] args){


        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        frame.setTitle("Bull's Eyes");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent component = new BullEyeComponent();
        frame.add(component);
        frame.setVisible(true);

    }
}
