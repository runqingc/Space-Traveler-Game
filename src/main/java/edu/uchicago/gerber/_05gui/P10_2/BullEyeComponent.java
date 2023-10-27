package P10_2;

import javax.swing.*;
import java.awt.*;

public class BullEyeComponent extends JComponent {


    public void paintComponent(Graphics g){

        drawBullEyes(g, 50, 50, 100);

    }


    public void drawBullEyes(Graphics g, int xLeft, int yTop, int radius){

        g.setColor(Color.BLACK);
        g.fillOval(xLeft, yTop, 2*radius, 2*radius);

        g.setColor(Color.WHITE);
        g.fillOval(xLeft+radius/5, yTop+radius/5, radius*8/5, radius*8/5);

        g.setColor(Color.BLACK);
        g.fillOval(xLeft+radius*2/5, yTop+radius*2/5, radius*6/5, radius*6/5);

        g.setColor(Color.WHITE);
        g.fillOval(xLeft+radius*3/5, yTop+radius*3/5, radius*4/5, radius*4/5);

        g.setColor(Color.BLACK);
        g.fillOval(xLeft+radius*4/5, yTop+radius*4/5, radius*2/5, radius*2/5);
    }

}
