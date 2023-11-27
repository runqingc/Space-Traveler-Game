package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserRed extends Laser{

    private int index = 0;


    // every 1 second fire once
    public static final int RED_FIRE_INTERVAL=Game.FRAMES_PER_SECOND;

    public final double FIRE_POWER = 15.0;
    public final double thrust = 3.0;

    public enum LaserType {

        LEFT_WING,
        RIGHT_WING,
        }
    private LaserType laserType;

    public LaserRed(Falcon falcon, LaserType type){

        setTeam(Team.FRIEND);
        laserType = type;
        setRadius(20);
        DAMAGE = 50;
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserRed04.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserRed06.png"));
        rasterMap.put(2, loadGraphic("/imgs/laser/laserRed07.png"));
        rasterMap.put(3, loadGraphic("/imgs/laser/laserRed16.png"));
        rasterMap.put(4, loadGraphic("/imgs/laser/laserRed01.png"));
        setRasterMap(rasterMap);
        setExpiry(rasterMap.size() * 12);



        switch (laserType){

            case RIGHT_WING:
                setOrientation(0);
                setCenter(new Point(falcon.getCenter().x+60, falcon.getCenter().y+10));
                setDeltaY(FIRE_POWER);

                break;
            case LEFT_WING:
                setOrientation(0);
                setCenter(new Point(falcon.getCenter().x-60, falcon.getCenter().y+10));
                setDeltaY(FIRE_POWER);
                break;

            default:
                break;

        }

    }

    private boolean isOutOfFrame() {
        return getCenter().x < 0 || getCenter().x > Game.DIM.width ||
                getCenter().y < 0 || getCenter().y > Game.DIM.height;
    }

    @Override
    public void move() {
        super.move(); // Retains the basic movement logic from the Sprite class
        // Check if the LaserRed is outside the game frame

        setDeltaY(getDeltaY()-thrust);
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
        if (getExpiry() % 12 == 0) index++;

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
