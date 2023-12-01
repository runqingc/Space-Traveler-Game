package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class StarGold extends Floater{


    // generate every 3 seconds
    public static final int SPAWN_STAR_GOLD = Game.FRAMES_PER_SECOND * 3;

    public StarGold(){

        setRadius(20);

        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/powerUp/star_gold.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaX(0);
        setDeltaY(2);

        // do not expire until out of frame
        setExpiry(5000);
    }

    public StarGold(EnemyShip enemyShip){
        setRadius(20);

        setCenter(enemyShip.getCenter());

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/powerUp/star_gold.png"));
        setRasterMap(rasterMap);

        // remain in the old place
        setDeltaX(0);
        setDeltaY(0);

        // do not expire until out of frame
        setExpiry(300);
    }


    private boolean isOutOfFrame() {
        return getCenter().x < 0 || getCenter().x > Game.DIM.width ||
                getCenter().y < 0 || getCenter().y > Game.DIM.height;
    }

    @Override
    public void move() {
        super.move(); // Retains the basic movement logic from the Sprite class
        // Check if the LaserBlue is outside the game frame
        if (isOutOfFrame()) {
            // Set expiry to 1 so it will be removed in the next game cycle
            setExpiry(1);
        }

    }


    @Override
    public void draw(Graphics g){

        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }








}
