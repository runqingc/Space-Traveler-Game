package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserBlue extends Laser{


    private int index = 0;



    public static final int BLUE_FIRE_INTERVAL=Game.FRAMES_PER_SECOND/2;

    // where does the laser shoot from
    public enum LaserType {
        MIDDLE,
        LEFT_WING,
        LEFT,
        RIGHT,
        RIGHT_WING,
        RAIN}
    private LaserType laserType;

    public LaserBlue(Falcon falcon, LaserType type) {

        setTeam(Team.FRIEND);
        laserType = type;
        setRadius(5);
        DAMAGE = 20;

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserBlue04.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserBlue05.png"));
        rasterMap.put(2, loadGraphic("/imgs/laser/laserBlue16.png"));
        rasterMap.put(3, loadGraphic("/imgs/laser/laserBlue01.png"));
        setRasterMap(rasterMap);

        if(laserType==LaserType.RAIN){
            setExpiry(rasterMap.size() * 15);
        }else{
            setExpiry(rasterMap.size() * 8);
        }

        final double FIRE_POWER = 35.0;
        final double RAIN_POWER = 20.0;


        double vectorX;
        double vectorY;

        switch (laserType){

            case MIDDLE:
                setCenter(falcon.getCenter());
                setDeltaY(-FIRE_POWER);
                break;
            case LEFT_WING:
                setCenter(new Point(falcon.getCenter().x-40, falcon.getCenter().y));
                setDeltaY(-FIRE_POWER);
                break;
            case RIGHT_WING:
                setCenter(new Point(falcon.getCenter().x+40, falcon.getCenter().y));
                setDeltaY(-FIRE_POWER);
                break;
            case LEFT:
                setCenter(new Point(falcon.getCenter().x-60, falcon.getCenter().y+10));
                // set orientation
                setOrientation(80);
                vectorX = Math.cos(Math.toRadians(getOrientation())) * FIRE_POWER;
                vectorY = Math.sin(Math.toRadians(getOrientation())) * FIRE_POWER;

                // Update the laser's speed (deltaX and deltaY)
                setDeltaX(getDeltaX() - vectorX);
                setDeltaY(getDeltaY() - vectorY);
                break;


            case RIGHT:
                setCenter(new Point(falcon.getCenter().x+60, falcon.getCenter().y+10));
                setOrientation(80);
                // set speed
                vectorX = Math.cos(Math.toRadians(getOrientation()))*FIRE_POWER;
                vectorY = Math.sin(Math.toRadians(getOrientation()))*FIRE_POWER;
                setDeltaX(getDeltaX() + vectorX);
                setDeltaY(getDeltaY() - vectorY);
                break;

            case RAIN:
                // random place in the top right of the space
                setCenter(new Point(Game.DIM.width-5, Game.R.nextInt(Game.DIM.height/3*2)));
                if(Game.R.nextInt()%2==0){
                    setCenter(new Point(Game.R.nextInt(Game.DIM.width), 5));
                }

                setOrientation(45);
                vectorX = Math.cos(Math.toRadians(getOrientation()))*RAIN_POWER;
                vectorY = Math.sin(Math.toRadians(getOrientation()))*RAIN_POWER;
                setDeltaX(getDeltaX() -vectorX);
                setDeltaY(getDeltaY() + vectorY);
                break;

            default:
                break;
        }

        // this line is potentially dangerous
//        setOrientation(0);

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
    public boolean isProtected() {
        return false;
    }

    @Override
    public void draw(Graphics g) {

        if(laserType==LaserType.RAIN){
            renderLaserRaster((Graphics2D) g, getRasterMap().get(index));
            //hold the image for two frames to slow down the dust cloud animation
            //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
            if (getExpiry() % 15 == 0) index++;
        }else{
            renderLaserRaster((Graphics2D) g, getRasterMap().get(index));
            //hold the image for two frames to slow down the dust cloud animation
            //we already have a simple decrement-to-zero counter with expiry; see move() method of Sprite.
            if (getExpiry() % 8 == 0) index++;
        }



    }

    private void renderLaserRaster(Graphics2D g2d, BufferedImage bufferedImage) {
        if(bufferedImage==null) return;
        double angleRadians = Math.toRadians(0);

        switch (laserType){

            case MIDDLE:
            case LEFT_WING:
            case RIGHT_WING:
                break;
            case LEFT:
                angleRadians = Math.toRadians(350);
                break;
            case RIGHT:
                angleRadians = Math.toRadians(10);
                break;
            case RAIN:
                angleRadians = Math.toRadians(225);
                break;
            default:
                break;
        }

        AffineTransform transform = new AffineTransform();
        transform.translate(getCenter().x, getCenter().y);
        transform.rotate(angleRadians);
        transform.translate(-bufferedImage.getWidth() / 2, -bufferedImage.getHeight() / 2);

        g2d.drawImage(bufferedImage, transform, null);

    }

}
