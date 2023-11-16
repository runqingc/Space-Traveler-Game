package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class GreyBullet02 extends Sprite{

    final double FIRE_POWER = 15.0;

    // which direction will the bullet come from
    public enum BulletType{
        S,
        SW,
        W,
        NW,
        N,
        NE,
        E,
        SE
    }

    private BulletType bulletType;

    public GreyBullet02(EnemyShip enemyShip, BulletType bulletType){

        setTeam(Team.FOE);

        setRadius(20);

        //the spin will be either plus or minus 0-9
        setSpin(somePosNegValue(10));


        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/Meteors/meteorGrey_small2.png"));
        setRasterMap(rasterMap);

        double vectorX;
        double vectorY;

        setCenter(enemyShip.getCenter());

        switch (bulletType){

            case S:
                setDeltaY(FIRE_POWER);
                break;
            case SW:
                setDeltaY(FIRE_POWER*0.7071);
                setDeltaX(-FIRE_POWER*0.7071);
                break;
            case W:
                setDeltaX(-FIRE_POWER);
                break;
            case NW:
                setDeltaY(-FIRE_POWER*0.7071);
                setDeltaX(-FIRE_POWER*0.7071);
                break;
            case N:
                setDeltaY(-FIRE_POWER);
                break;
            case NE:
                setDeltaY(-FIRE_POWER*0.7071);
                setDeltaX(FIRE_POWER*0.7071);
                break;
            case E:
                setDeltaX(FIRE_POWER);
                break;
            case SE:
                setDeltaY(FIRE_POWER*0.7071);
                setDeltaX(FIRE_POWER*0.7071);
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
