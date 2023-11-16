package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyYellowUFO extends EnemyShip{

    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND;

    // here should be some 0-7 random value
    public int lastFirePosition;
    public EnemyYellowUFO(){
        setRadius(70);
        health = this.getRadius();
        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));
        lastFirePosition = Game.R.nextInt(8);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/ufoYellow.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaY(3);
    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }

}
