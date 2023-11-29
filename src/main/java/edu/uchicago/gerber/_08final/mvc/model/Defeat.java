package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Defeat extends Sprite{

    public Defeat(){
        setTeam(Team.FOREGROUND);

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/UI/Defeat.png")); // Replace with your image path
        setRasterMap(rasterMap);

        setCenter(new Point(Game.DIM.width/2, Game.DIM.height/2));
        setDeltaY(0);
        setDeltaX(0);
    }


    @Override
    public void draw(Graphics g) {
        BufferedImage img = getRasterMap().get(0);

        if (img != null){
            Graphics2D g2d = (Graphics2D) g.create();

            // Draw the image with the scaling transformation applied
            g2d.drawImage(img, getCenter().x - img.getWidth() / 2 , getCenter().y - img.getHeight() / 2, null);

            g2d.dispose();
        }
    }





}
