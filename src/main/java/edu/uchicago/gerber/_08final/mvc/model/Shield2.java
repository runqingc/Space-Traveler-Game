package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.CommandCenter;
import edu.uchicago.gerber._08final.mvc.controller.GameOp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Shield2 extends Sprite{

    private final int flaoterProtectTime = 500;

    private Falcon falcon; // Reference to the Falcon object

    public Shield2(Falcon falcon) {

        this.falcon = falcon;
        falcon.setProtected(true);

        setTeam(Team.FRIEND);

        setRadius(falcon.getRadius()+50);
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/effects/shield2.png"));
        setRasterMap(rasterMap);
        setExpiry(flaoterProtectTime);
        setCenter(falcon.getCenter());
    }



    @Override
    public void draw(Graphics g) {
        renderShieldRaster((Graphics2D) g, getRasterMap().get(0));

    }

    @Override
    public void move() {
        // Ensure that the shield's position is always in sync with the Falcon
        setCenter(falcon.getCenter());

        if (this.getExpiry() <=1){
            System.out.println("sheild expire"+this.getExpiry());
            falcon.setProtected(false);
        }else {
            this.setExpiry(this.getExpiry() - 1);
        }

        super.move(); // Call the super class move method for any additional logic
    }

    @Override
    public boolean isProtected(){
        return true;
    }

    private void renderShieldRaster(Graphics2D g2d, BufferedImage bufferedImage) {
        if (bufferedImage == null) return;

        // Custom rendering logic for LaserBlue
        // This might involve different scaling, positioning, or other graphical adjustments
        // specific to the laser's appearance and behavior

        // Example: Simple rendering without scaling
        g2d.drawImage(bufferedImage, getCenter().x - bufferedImage.getWidth() / 2,
                getCenter().y - bufferedImage.getHeight() / 2, null);
    }
}
