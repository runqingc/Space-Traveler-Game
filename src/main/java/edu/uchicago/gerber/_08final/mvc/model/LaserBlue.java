package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserBlue extends Sprite{

    int track;

    private int index = 0;


    public LaserBlue(Falcon falcon, int track) {

        setTeam(Team.FRIEND);

        setRadius(5);

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserBlue04.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserBlue05.png"));
        rasterMap.put(2, loadGraphic("/imgs/laser/laserBlue16.png"));
        rasterMap.put(3, loadGraphic("/imgs/laser/laserBlue01.png"));
        setRasterMap(rasterMap);
        setExpiry(rasterMap.size() * 5);

        final double FIRE_POWER = 35.0;

        setCenter(falcon.getCenter());
        setDeltaY(falcon.getDeltaY() - FIRE_POWER);



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

        renderLaserRaster((Graphics2D) g, getRasterMap().get(index));
        //hold the image for two frames to slow down the dust cloud animation
        //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
        if (getExpiry() % 5 == 0) index++;

    }

    private void renderLaserRaster(Graphics2D g2d, BufferedImage bufferedImage) {
        if (bufferedImage == null) return;

        // Custom rendering logic for LaserBlue
        // This might involve different scaling, positioning, or other graphical adjustments
        // specific to the laser's appearance and behavior

        // Example: Simple rendering without scaling
        g2d.drawImage(bufferedImage, getCenter().x - bufferedImage.getWidth() / 2,
                getCenter().y - bufferedImage.getHeight() / 2, null);
    }

}
