package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Hint extends Sprite{

    public Hint(){
        setTeam(Team.BACKGROUND);
        setCenter(new Point(Game.DIM.width/2-200, -200));
        // slowly moving down the screen
        setDeltaY(2);
    }

    public void renderHintRaster(Graphics2D g2d, BufferedImage bufferedImage){

        double angleRadians = this.getOrientation();

        // Desired dimensions
        int desiredWidth = 600;
        int desiredHeight = 400;

        // Calculate scaling factors
        double scaleX = (double) desiredWidth / bufferedImage.getWidth();
        double scaleY = (double) desiredHeight / bufferedImage.getHeight();

        // Create the transformation
        AffineTransform transform = new AffineTransform();
        transform.translate(getCenter().x, getCenter().y);
        transform.rotate(angleRadians);

        // Apply scaling
        transform.scale(scaleX, scaleY);

        // Adjust translation to account for the new size
        transform.translate(-desiredWidth / 2.0, -desiredHeight / 2.0);

        // Draw the image with the transform
        g2d.drawImage(bufferedImage, transform, null);
    }

    private boolean isOutOfFrame() {
        return getCenter().y > Game.DIM.height;
    }

    @Override
    public void move() {
        Point center = getCenter();

        double newXPos = center.x + getDeltaX();
        double newYPos = center.y + getDeltaY();
        setCenter(new Point((int) newXPos, (int) newYPos));
        // Check if the LaserBlue is outside the game frame
        if (isOutOfFrame()) {
            // Set expiry to 1 so it will be removed in the next game cycle
            setExpiry(1);
        }

    }


}
