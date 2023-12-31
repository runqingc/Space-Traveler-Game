package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ShieldFloater extends Floater {
	//spawn every 23 seconds
	public static final int SPAWN_SHIELD_FLOATER = Game.FRAMES_PER_SECOND * 23;
	public ShieldFloater() {

		Map<Integer, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(0, loadGraphic("/imgs/powerUp/powerupBlue_shield.png"));
		setRasterMap(rasterMap);

		setExpiry(260);
	}

	@Override
	public void draw(Graphics g){
		renderRaster((Graphics2D) g, getRasterMap().get(0));
	}



}
