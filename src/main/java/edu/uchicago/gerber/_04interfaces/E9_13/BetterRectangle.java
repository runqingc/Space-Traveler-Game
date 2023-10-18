package edu.uchicago.gerber._04interfaces.E9_13;

import java.awt.*;

public class BetterRectangle extends Rectangle {

    BetterRectangle(int x, int y, int h, int w){
        super.setLocation(x, y);
        super.setSize(w, h);
    }

    public double getPerimeter(){
        return 2*(super.getHeight()+super.getWidth());
    }

    public double getArea(){
        return super.getHeight()*super.getWidth();
    }

}
