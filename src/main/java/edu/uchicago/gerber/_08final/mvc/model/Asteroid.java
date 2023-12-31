package edu.uchicago.gerber._08final.mvc.model;


import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.awt.*;

import edu.uchicago.gerber._08final.mvc.controller.Game;


public class Asteroid extends Sprite {

	//radius of a large asteroid
	private final int LARGE_RADIUS = 110;

	public static int[] SPAWN_TIME = {Game.FRAMES_PER_SECOND * 20, Game.FRAMES_PER_SECOND * 20,
			Game.FRAMES_PER_SECOND * 15, Game.FRAMES_PER_SECOND * 15, Game.FRAMES_PER_SECOND * 15, Game.FRAMES_PER_SECOND * 15};

	private int pictureIndex = 0;

	//size determines if the Asteroid is Large (0), Medium (1), or Small (2)
	public Asteroid(int size){

		// my modification: always place the new asteroid at the top of the screen
		setCenter(new Point(Game.R.nextInt(Game.DIM.width), 0));

		//a size of zero is a big asteroid
		//a size of 1 or 2 is med or small asteroid respectively. See getSize() method.
		if (size == 0) setRadius(LARGE_RADIUS);
		else setRadius(LARGE_RADIUS/(size * 2));

		//Asteroid is FOE
		setTeam(Team.FOE);


		//the spin will be either plus or minus 0-9
		setSpin(somePosNegValue(10));
		//random delta-x
		setDeltaX(somePosNegValue(10));
		//random delta-y
		setDeltaY(somePosNegValue(10));


		Map<Integer, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(0, loadGraphic("/imgs/Meteors/meteorBrown_big1.png"));
		rasterMap.put(1, loadGraphic("/imgs/Meteors/meteorBrown_big2.png"));
		rasterMap.put(2, loadGraphic("/imgs/Meteors/meteorBrown_big3.png"));
		rasterMap.put(3, loadGraphic("/imgs/Meteors/meteorBrown_big4.png"));
		setRasterMap(rasterMap);

		// uncomment to produce different types of meteors
//		pictureIndex = (Game.R.nextInt())%4;
	}



	//overloaded constructor, so we can spawn smaller asteroids from an exploding one
	public Asteroid(Asteroid astExploded){
		//calls the other constructor: Asteroid(int size)
		this(astExploded.getSize() + 1);
		setCenter(astExploded.getCenter());
		int newSmallerSize = astExploded.getSize() + 1;
		//random delta-x : inertia + the smaller the asteroid, the faster its possible speed
		setDeltaX(astExploded.getDeltaX() / 1.5 + somePosNegValue( 5 + newSmallerSize * 2));
		//random delta-y : inertia + the smaller the asteroid, the faster its possible speed
		setDeltaY(astExploded.getDeltaY() / 1.5 + somePosNegValue( 5 + newSmallerSize * 2));

	}

	//converts the radius to integer representing the size of the Asteroid:
	//0 = large, 1 = medium, 2 = small
	public int getSize(){
		switch (getRadius()) {
			case LARGE_RADIUS:
				return 0;
			case LARGE_RADIUS / 2:
				return 1;
			case LARGE_RADIUS / 4:
				return 2;
			default:
				return 0;
		}
	}


	@Override
	public void draw(Graphics g) {

		renderRaster((Graphics2D) g, getRasterMap().get(pictureIndex));

	}



}
