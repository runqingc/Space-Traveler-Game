package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

// enemyShip is coming!!!
public class EnemyBlack3 extends Sprite{


    public EnemyBlack3() {

        setTeam(Team.FOE);

        setRadius(100);

        // suppose the Enemy will always comes from top
        setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/enemyBlack3.png"));
        setRasterMap(rasterMap);

        // slowly moving down the screen
        setDeltaY(2);
    }

    @Override
    public void draw(Graphics g) {

        renderRaster((Graphics2D) g, getRasterMap().get(0));

    }




}
