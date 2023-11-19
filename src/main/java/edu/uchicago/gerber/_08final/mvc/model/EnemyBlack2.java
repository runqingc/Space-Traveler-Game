package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyBlack2 extends EnemyShip{


    // set shooting interval of the enemy ship
    // fire every 2 seconds

    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND * 2;

    public static int[] SPAWN_TIME = {-1, Game.FRAMES_PER_SECOND * 9,
            Game.FRAMES_PER_SECOND * 11, Game.FRAMES_PER_SECOND * 11, Game.FRAMES_PER_SECOND * 13, Game.FRAMES_PER_SECOND * 11};


    // the health is related to its radius


    public EnemyBlack2(){

        super();

        setRadius(60);



        health = this.getRadius();

        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));

        // set up picture
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/enemyBlack2.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaY(2);

    }



    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }



}
