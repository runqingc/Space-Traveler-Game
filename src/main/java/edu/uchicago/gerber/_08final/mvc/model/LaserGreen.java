package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserGreen extends Laser {

    private double orbitRadius; // The radius of the orbit
    private Falcon falcon; // The Falcon object that the laser orbits around



    public static final int GREEN_FIRE_INTERVAL= Game.FRAMES_PER_SECOND ;

    public LaserGreen(Falcon falcon) {
        this.falcon = falcon;
        ++falcon.greenLaserNumber;
        setTeam(Team.FRIEND);
        setRadius(15); // Size of the laser
        DAMAGE = 20;
        orbitRadius = 100; // Set the radius of the orbit
        setOrientation(0); // Start angle

        // Load the green laser image
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserGreen10.png")); // Replace with your image path
        setRasterMap(rasterMap);


        // Initial position of the laser
        updatePosition();
    }

    // Update the laser position to be at the correct spot in its orbit around the Falcon
    private void updatePosition() {
        double radianAngle = Math.toRadians(getOrientation());
        int xOffset = (int) (orbitRadius * Math.cos(radianAngle));
        int yOffset = (int) (orbitRadius * Math.sin(radianAngle));
        setCenter(new Point(falcon.getCenter().x + xOffset, falcon.getCenter().y + yOffset));
    }

    @Override
    public void move() {
        // Update the angle for the next frame
        setOrientation((getOrientation()+10)%360); // Controls the speed of the orbit


        // Update the laser's position based on the new angle
        updatePosition();
        // No need to call super.move() since we don't use the normal movement logic
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage img = getRasterMap().get(0);

        if (img != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Calculate the center of the laser
            int imgCenterX = getCenter().x;
            int imgCenterY = getCenter().y;

            // Convert orbit angle to radians for the rotation
            double rotationRequired = Math.toRadians(getOrientation());

            // Rotate around the center of the laser image
            AffineTransform tx = AffineTransform.getRotateInstance(
                    rotationRequired, imgCenterX, imgCenterY
            );

            // Draw the rotated image
            g2d.setTransform(tx);
            g2d.drawImage(img, imgCenterX - img.getWidth() / 2, imgCenterY - img.getHeight() / 2, null);

        }
    }

}
