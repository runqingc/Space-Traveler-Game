package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RedBoltFloater extends Floater{


    public static final int SPAWN_RED_BOLT_FLOATER = Game.FRAMES_PER_SECOND * 17;

    public RedBoltFloater(){

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/powerUp/powerupRed_bolt.png"));
        setRasterMap(rasterMap);
        setExpiry(260);

    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }

}
