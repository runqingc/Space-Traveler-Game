package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserGreenRain extends Sprite{


    public final int DAMAGE = 20;

    public static int POS = Game.DIM.width;


    public LaserGreenRain(int x){

        setTeam(Team.FRIEND);

        setRadius(15); // Size of the laser

        setOrientation(0); // Start angle

        // Load the green laser image
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserGreen10up.png")); // Replace with your image path
        setRasterMap(rasterMap);

        setCenter(new Point(x, Game.DIM.height-10));

        setDeltaY(-20);
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

            renderLaserRaster((Graphics2D) g, img);
        }


    }

    private void renderLaserRaster(Graphics2D g2d, BufferedImage bufferedImage){

        double angleRadians = this.getOrientation();
        AffineTransform transform = new AffineTransform();
        transform.translate(getCenter().x, getCenter().y);
        transform.rotate(angleRadians);
        transform.translate(-bufferedImage.getWidth() / 2, -bufferedImage.getHeight() / 2);

        g2d.drawImage(bufferedImage, transform, null);

    }


}
