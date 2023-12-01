package edu.uchicago.gerber._08final.mvc.model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class LaserRedRain extends Laser{

    public static int angle = 0;


    public LaserRedRain(){

        setTeam(Team.FRIEND);

        setRadius(5);
        DAMAGE = 20;

        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/laser/laserRed06.png"));
        rasterMap.put(1, loadGraphic("/imgs/laser/laserRed16.png"));
        rasterMap.put(2, loadGraphic("/imgs/laser/laserRed07.png"));
        rasterMap.put(3, loadGraphic("/imgs/laser/laserRed01.png"));
        setRasterMap(rasterMap);

    }


    @Override
    public void draw(Graphics g) {

    }

    private void renderLaserRaster(Graphics2D g2d, BufferedImage bufferedImage) {
        if(bufferedImage==null) return;
        double angleRadians = Math.toRadians(angle);
        angle += 5;

        AffineTransform transform = new AffineTransform();
        transform.translate(getCenter().x, getCenter().y);
        transform.rotate(angleRadians);
        transform.translate(-bufferedImage.getWidth() / 2, -bufferedImage.getHeight() / 2);

        g2d.drawImage(bufferedImage, transform, null);

    }
}
