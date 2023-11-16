package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class GreyBullet02 extends Sprite{

    final double FIRE_POWER = 15.0;

    public enum BulletType{

        MIDDLE,
        LEFT_WING,
        RIGHT_WING,
    }

    private BulletType bulletType;

    public GreyBullet02(EnemyShip enemyShip, BulletType bulletType){

        setTeam(Team.FOE);

        setRadius(20);

        // set last fire time
        enemyShip.lastFireTime = System.currentTimeMillis();

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/ufoYellow.png"));
        setRasterMap(rasterMap);

        double vectorX;
        double vectorY;

        switch (bulletType){

            case MIDDLE:
                setCenter(enemyShip.getCenter());
                setDeltaY(FIRE_POWER);
                break;
            case LEFT_WING:
                break;
            case RIGHT_WING:
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
        // Check if the LaserBlue is outside the game frame
        if (isOutOfFrame()) {
            // Set expiry to 1 so it will be removed in the next game cycle
            setExpiry(1);
        }

    }

    @Override
    public void draw(Graphics g) {
        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }
}
