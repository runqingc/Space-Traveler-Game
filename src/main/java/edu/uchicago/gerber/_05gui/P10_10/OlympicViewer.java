package P10_10;

import javax.swing.*;

public class OlympicViewer {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setSize(800, 400);

        frame.setTitle("Olympic Flag");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent component = new OlympicComponent();
        frame.add(component);
        frame.setVisible(true);

    }
}
