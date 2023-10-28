package P11_9;

import javax.swing.*;
import java.awt.*;

public class CircleComponent extends JComponent {

    private int centerX;
    private int centerY;
    private int radius;


    public void paintComponent(Graphics g){

        g.drawOval(centerX-radius, centerY-radius, 2*radius, 2*radius);

    }


}
