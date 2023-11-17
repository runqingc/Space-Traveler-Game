package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserGreenDebris extends Sprite{


    private int index = 0;

    public LaserGreenDebris(Sprite explodingSprite){

        setTeam(Team.DEBRIS);

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserGreen15.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserGreen14.png"));
        setRasterMap(rasterMap);

        setExpiry(rasterMap.size() * 2);

        setCenter(explodingSprite.getCenter());
        setDeltaX(0);
        setDeltaY(0);
        setRadius(20);
        //the spin will be either plus or minus 0-9
        setSpin(somePosNegValue(10));

    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(index));
        //hold the image for two frames to slow down the dust cloud animation
        //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
        if (getExpiry() % 2 == 0) index++;
    }
}
