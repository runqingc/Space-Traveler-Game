package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyBlack1 extends EnemyShip{



    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND * 2;

    public static int[] SPAWN_TIME = {-1, -1,
            Game.FRAMES_PER_SECOND * 6, Game.FRAMES_PER_SECOND * 16, Game.FRAMES_PER_SECOND * 17, Game.FRAMES_PER_SECOND * 18};

    public EnemyBlack1(){

        setRadius(80);

        health = this.getRadius();

        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));

        // set up picture
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/enemyBlack1.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaY(2);
    }


    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }
}
