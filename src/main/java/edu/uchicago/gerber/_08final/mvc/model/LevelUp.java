package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LevelUp extends Sprite{

    public LevelUp(){
        setTeam(Team.BACKGROUND);

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/UI/LevelUp.png")); // Replace with your image path
        setRasterMap(rasterMap);

        setCenter(new Point(Game.DIM.width/2, Game.DIM.height));

        setDeltaY(-10git);

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
