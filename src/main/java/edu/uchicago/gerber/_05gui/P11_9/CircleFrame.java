package P11_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CircleFrame extends JFrame {

    private Point center;

    private double radius;

    private boolean numClicked;



    public CircleFrame(){

        setSize(500, 500);
        setTitle("Use 2 clicks to draw the circle!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        center = null;
        numClicked = false;

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

    }

    class DrawingPanel extends JPanel{

        public DrawingPanel(){
            MouseListener mouseListener= new DrawCircleMouseListener();
            addMouseListener(mouseListener);
        }

        public void paintComponent(Graphics g){
            if(center!=null){
                g.drawOval((int) (center.x-radius), (int) (center.y-radius),
                        (int) (2*radius), (int) (2*radius));
            }else{
                center = new Point();
            }

        }

        class DrawCircleMouseListener implements MouseListener{

            @Override
            public void mousePressed(MouseEvent e) {
                if(!numClicked){
                    center.x = e.getX();
                    center.y = e.getY();
                }else{
                    radius = center.distance(e.getX(), e.getY());
                    repaint();
                }
                numClicked = !numClicked;
            }

            //empty methods
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }

    }

}
