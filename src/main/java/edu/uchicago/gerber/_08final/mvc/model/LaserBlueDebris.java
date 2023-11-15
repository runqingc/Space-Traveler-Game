package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserBlueDebris extends Sprite{


    private int index = 0;

    public LaserBlueDebris(Sprite explodingSprite){

        setTeam(Team.DEBRIS);



        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserBlue09.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserBlue08.png"));
        setRasterMap(rasterMap);

        setExpiry(rasterMap.size() * 2);
        setSpin(explodingSprite.getSpin());
        setCenter(explodingSprite.getCenter());
        setDeltaX(0);
        setDeltaY(0);
        setRadius((int) (20));

    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(index));
        //hold the image for two frames to slow down the dust cloud animation
        //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
        if (getExpiry() % 2 == 0) index++;

    }
}
