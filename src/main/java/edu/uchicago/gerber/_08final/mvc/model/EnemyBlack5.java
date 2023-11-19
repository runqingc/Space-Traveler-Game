package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyBlack5 extends EnemyShip{

    public int lastFirePosition;

    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND * 3/2;
    public EnemyBlack5(){
        setRadius(90);
        health = this.getRadius()+20;
        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/enemyBlack5.png"));
        setRasterMap(rasterMap);
        lastFirePosition = 1;

        // slowly moving down the screen
        setDeltaY(2);

    }


    @Override
    public void draw(Graphics g) {

        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }
}
