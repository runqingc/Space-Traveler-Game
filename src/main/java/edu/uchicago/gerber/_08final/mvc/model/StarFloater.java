package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class StarFloater extends Floater{


    //spawn every 29 seconds
    public static final int SPAWN_STAR_FLOATER = Game.FRAMES_PER_SECOND * 29;

    public StarFloater(){

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/powerUp/powerupBlue_star.png"));
        setRasterMap(rasterMap);
        setExpiry(260);

    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }

}
