package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyBOSS extends EnemyShip{


    int direction = 1;

    public EnemyBOSS(){

        setRadius(100);

        health = 10000;

        setCenter(new Point(Game.DIM.width/2, -200));

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/enemy/BOSS.png"));
        setRasterMap(rasterMap);


        setDeltaY(1.5);

    }

    @Override
    public void move() {

        //The following code block just keeps the sprite inside the bounds of the frame.
        //To ensure this behavior among all sprites in your game, make sure to call super.move() in extending classes
        // where you need to override the move() method.

        Point center = getCenter();

        //right-bounds reached
        if (center.x > Game.DIM.width) {
            setCenter(new Point(1, center.y));
            //left-bounds reached
        } else if (center.x < 0) {
            setCenter(new Point(Game.DIM.width - 1, center.y));
            //bottom-bounds reached
        } else if (center.y > Game.DIM.height) {
            setCenter(new Point(center.x, 1));
            //top-bounds reached
        } else {
            double newXPos = center.x + getDeltaX();
            double newYPos = center.y + getDeltaY();
            setCenter(new Point((int) newXPos, (int) newYPos));
        }


        // Check if the LaserBlue is outside the game frame
        if(this.getCenter().y>=200){
            setDeltaY(0);
        }

    }


    @Override
    public void draw(Graphics g) {

        renderRaster((Graphics2D) g, getRasterMap().get(0));
    }

    @Override
    protected void renderRaster(Graphics2D g2d, BufferedImage bufferedImage) {

        if (bufferedImage == null) return;

        // Calculate the new width and height while maintaining the aspect ratio
        int newWidth = (int)(bufferedImage.getWidth() * 3);
        int newHeight = (int)(bufferedImage.getHeight() * 3);

        // Create a new BufferedImage with the new dimensions
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());

        // Draw the original image to the new image while applying the scaling
        Graphics2D g2dResized = resizedImage.createGraphics();
        g2dResized.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
        g2dResized.dispose();

        // Calculate the drawing location so that resizedImage is centered on getCenter().x and getCenter().y
        int drawLocationX = getCenter().x - newWidth / 2;
        int drawLocationY = getCenter().y - newHeight / 2;

        // Now draw the resized image onto the Graphics2D context provided at the new location
        g2d.drawImage(resizedImage, drawLocationX, drawLocationY, null);
    }



}
