package P10_5;

import javax.swing.*;
import java.awt.*;

public class HouseComponent extends JComponent {

    public void paintComponent(Graphics g){

        drawHouse(g, 50, 50, 50, Color.RED, Color.BLUE, Color.BLACK, Color.ORANGE);
        drawHouse(g, 350, 150, 30, Color.ORANGE, Color.PINK, Color.PINK, Color.BLUE);
    }

    public void drawHouse(Graphics g, int xLeft, int yTop, int size,
                          Color wallColor, Color windowColor, Color roofColor, Color doorColor){

            g.setColor(roofColor);
            g.drawLine(xLeft, yTop+2*size, xLeft+2*size, yTop);
            g.drawLine(xLeft+2*size, yTop, xLeft+4*size, yTop+2*size);

            g.setColor(wallColor);
            g.drawLine(xLeft, yTop+2*size, xLeft+4*size, yTop+2*size);
            g.drawLine(xLeft+4*size, yTop+2*size, xLeft+4*size, yTop+5*size);
            g.drawLine(xLeft+4*size, yTop+5*size, xLeft, yTop+5*size);
            g.drawLine(xLeft, yTop+5*size, xLeft, yTop+2*size);

            g.setColor(doorColor);
            g.drawLine(xLeft+size/2, yTop+3*size, xLeft+size*3/2, yTop+3*size);
            g.drawLine(xLeft+size*3/2, yTop+3*size, xLeft+size*3/2, yTop+5*size);
            g.drawLine(xLeft+size/2, yTop+3*size, xLeft+size/2, yTop+5*size);

            g.setColor(windowColor);
            g.drawRect(xLeft+size*5/2, yTop+16*size/5, size, size);


    }

}
