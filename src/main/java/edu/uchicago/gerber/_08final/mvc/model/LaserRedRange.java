package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;

public class LaserRedRange extends Laser{



    public LaserRedRange(Sprite explodingSprite){

        setTeam(Team.FRIEND);
        setCenter(explodingSprite.getCenter());
        setDeltaX(0);
        setDeltaY(0);
        setRadius(50);
        DAMAGE = 50;
    }

    @Override
    public void draw(Graphics g) {

    }
}
