package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class HintArrowControl extends Hint{

    public static boolean appeared = false;

    public static int index = 0;

    public static int SPAWN_HINT = Game.FRAMES_PER_SECOND ;

    private int thisIndex;
    public HintArrowControl(){
        super();
        Map<Integer, BufferedImage> rasterMap = new HashMap<>();
        rasterMap.put(0, loadGraphic("/imgs/UI/Hint1.png")); // Replace with your image path
        rasterMap.put(1, loadGraphic("/imgs/UI/Hint2.png")); // Replace with your image path
        rasterMap.put(2, loadGraphic("/imgs/UI/Hint3.png")); // Replace with your image path
        setRasterMap(rasterMap);
        thisIndex = index;
    }
    @Override
    public void draw(Graphics g) {
        BufferedImage img = getRasterMap().get(thisIndex);

        if (img != null){

            renderHintRaster((Graphics2D) g, img);
        }
    }

}
