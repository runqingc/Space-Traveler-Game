package P10_9;

import javax.swing.*; // Import the JComponent class
import java.awt.*;


public class ItalianFlagComponent extends JComponent {
        public void paintComponent(Graphics g) {

            drawGermanFlag(g, 100, 100, 120);

            drawHungarianFlag(g, 100, 200, 120);

            drawItalianFlag(g, 100, 300, 120);

        }

        public void drawItalianFlag(Graphics g, int xLeft, int yTop, int width){

            g.setColor(Color.BLACK);
            g.drawLine(xLeft+width/3, yTop, xLeft+width*2/3, yTop);
            g.drawLine(xLeft+width/3, yTop+width*2/3, xLeft+width*2/3, yTop+width*2/3);

            g.setColor(Color.GREEN);
            g.fillRect(xLeft, yTop, width/3, width*2/3);
            g.setColor(Color.RED);
            g.fillRect(xLeft+width*2/3, yTop, width/3, width*2/3);
        }

        public void drawGermanFlag(Graphics g, int xLeft, int yTop, int width){

            g.setColor(Color.BLACK);
            g.fillRect(xLeft, yTop, width, width/5);
            g.setColor(Color.RED);
            g.fillRect(xLeft, yTop+width/5, width, width/5);
            g.setColor(Color.YELLOW);
            g.fillRect(xLeft, yTop+width*2/5, width, width/5);

        }
        //Hungarian
        public void drawHungarianFlag(Graphics g, int xLeft, int yTop, int width){

            g.setColor(Color.RED);
            g.fillRect(xLeft, yTop, width, width/5);
            g.setColor(Color.WHITE);
            g.fillRect(xLeft, yTop+width/5, width, width/5);
            g.setColor(Color.GREEN);
            g.fillRect(xLeft, yTop+width*2/5, width, width/5);

        }


    }

