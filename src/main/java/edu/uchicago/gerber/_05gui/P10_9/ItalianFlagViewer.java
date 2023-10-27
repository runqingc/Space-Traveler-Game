package P10_9;

import javax.swing.*;

public class ItalianFlagViewer {

    public static void main(String[] args){


        JFrame frame = new JFrame();
        frame.setSize(400, 200);

        frame.setTitle("GermanFlag, HungarianFlag and ItalianFlag");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent component = new ItalianFlagComponent();
        frame.add(component);
        frame.setVisible(true);

    }


}
