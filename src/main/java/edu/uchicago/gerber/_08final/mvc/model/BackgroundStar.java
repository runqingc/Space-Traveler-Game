package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;

public class BackgroundStar extends Sprite{

    private Color color;

    // false is becoming dark, and true is becoming light
    int trend = -10;

    private int radius;

    public BackgroundStar(){

        // star is DEBRIS, do not interact
        setTeam(Team.BACKGROUND);
        setRadius(10);
        int bright = Game.R.nextInt(223); //Stars are muted at max brightness of 225 out of 255
        color = new Color(bright, bright, bright); //some grey value

        if(bright%2==0){
            trend *= -1;
        }
        // slowly moving down the screen
        setDeltaY(2);

    }

    @Override
    public void draw(Graphics g) {



        g.setColor(color);
        g.fillOval(getCenter().x, getCenter().y, getRadius(), getRadius());


    }
}
