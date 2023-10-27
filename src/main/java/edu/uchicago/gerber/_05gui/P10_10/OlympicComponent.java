package P10_10;

import javax.swing.*;
import java.awt.*;

public class OlympicComponent extends JComponent {

    public void paintComponent(Graphics g){

        drawRing(g, 100, 100, 50, Color.BLUE);
        drawRing(g, 208, 100, 50, Color.BLACK);
        drawRing(g, 316, 100, 50, Color.RED);
        drawRing(g, 150, 150, 50, Color.YELLOW);
        drawRing(g, 258, 150, 50, Color.GREEN);
    }

    public void drawRing(Graphics g, int xLeft, int yTop, int radius, Color color){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        float thickness = (float) (radius*0.16); // Set the desired thickness
        g2d.setStroke(new BasicStroke(thickness));

        g2d.drawOval(xLeft, yTop, radius*2, radius*2);

    }

    public void drawOlympic(Graphics g, int xLeft, int yTop, int radius){

        g.setColor(Color.BLUE);
        g.drawOval(xLeft, yTop, radius*2, radius*2);
//        g.setColor(Color.WHITE);
//        g.drawOval(xLeft+radius/10, yTop+radius/10, radius*18/10, radius*18/10);
        g.setColor(Color.BLACK);
        g.drawOval(xLeft+radius*2, yTop, radius*2, radius*2);
//        g.setColor(Color.WHITE);
//        g.drawOval(xLeft+radius/10+radius*2, yTop+radius/10, radius*18/10, radius*18/10);
        g.setColor(Color.RED);
        g.drawOval(xLeft+radius*4, yTop, radius*2, radius*2);
//        g.setColor(Color.WHITE);
//        g.drawOval(xLeft+radius/10+radius*4, yTop+radius/10, radius*18/10, radius*18/10);
        g.setColor(Color.YELLOW);
        g.drawOval(xLeft+radius, yTop+radius, radius*2, radius*2);
//        g.setColor(Color.WHITE);
//        g.drawOval(xLeft+radius+radius/10, yTop+radius+radius/10, radius*18/10, radius*18/10);
        g.setColor(Color.GREEN);
        g.drawOval(xLeft+radius+2*radius, yTop+radius, radius*2, radius*2);
//        g.setColor(Color.WHITE);
//        g.drawOval(xLeft+radius+radius/10+2*radius, yTop+radius+radius/10, radius*18/10, radius*18/10);

    }

}
