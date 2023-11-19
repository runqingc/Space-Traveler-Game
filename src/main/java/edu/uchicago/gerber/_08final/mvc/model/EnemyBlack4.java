package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyBlack4 extends EnemyShip{


    // need to set its fire logic
    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND * 3;

    public static int[] SPAWN_TIME = {-1, -1, -1, -1, Game.FRAMES_PER_SECOND * 10, Game.FRAMES_PER_SECOND * 14};

    public EnemyBlack4(){

        super();

        setRadius(100);

        health = getRadius()+50;

        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));


        // set up picture
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/enemyBlack4.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaY(2);


    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }
}
