package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RedCloudDebris extends Sprite{

    private int index = 0;

    public RedCloudDebris(Sprite explodingSprite){

        setTeam(Team.DEBRIS);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/exp/Effect_more_red1.png") );
        rasterMap.put(1, loadGraphic("/imgs/exp/Effect_more_red2.png") );
        rasterMap.put(2, loadGraphic("/imgs/exp/Effect_more_red3.png") );
        rasterMap.put(3, loadGraphic("/imgs/exp/Effect_more_red4.png") );
        rasterMap.put(4, loadGraphic("/imgs/exp/Effect_more_red5.png") );
        rasterMap.put(5, loadGraphic("/imgs/exp/Effect_more_red6.png") );
        rasterMap.put(6, loadGraphic("/imgs/exp/Effect_more_red7.png") );
        rasterMap.put(7, loadGraphic("/imgs/exp/Effect_more_red8.png") );
        rasterMap.put(8, loadGraphic("/imgs/exp/Effect_more_red9.png") );

        setRasterMap(rasterMap);
        //expire it out after it has done its animation. Multiply by 2 to slow down the animation
        setExpiry(rasterMap.size() * 2);

        //everything is relative to the exploding sprite
        setSpin(explodingSprite.getSpin());
        setCenter(explodingSprite.getCenter());
        setDeltaX(explodingSprite.getDeltaX());
        setDeltaY(explodingSprite.getDeltaY());
        setRadius((int) (explodingSprite.getRadius()));
    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(index));
        //hold the image for two frames to slow down the dust cloud animation
        //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
        if (getExpiry() % 2 == 0) index++;
    }


}
