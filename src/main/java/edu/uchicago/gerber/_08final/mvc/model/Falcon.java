package edu.uchicago.gerber._08final.mvc.model;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

@Data
public class Falcon extends Sprite {

	// ==============================================================
	// FIELDS 
	// ==============================================================

	//static fields

	//number of degrees the falcon will turn at each animation cycle if the turnState is LEFT or RIGHT
	public final static int TURN_STEP = 11;
	//number of frames that the falcon will be protected after a spawn
	public static final int INITIAL_SPAWN_TIME = 1;
	//number of frames falcon will be protected after consuming a NewShieldFloater
//	public static final int MAX_SHIELD = 200;
	public static final int MAX_NUKE = 600;

	public static final int MIN_RADIUS = 28;

	boolean isProtected = false;


	//images states
	public enum ImageState {
		FALCON_INVISIBLE, //for pre-spawning
		FALCON, //normal ship
		FALCON_THR, //normal ship thrusting
		FALCON_PRO, //protected ship (green)
		FALCON_PRO_THR, //protected ship (green) thrusting

	}

	private int laserLevel = 1;
	private int redLaserLevel = 0;
	public int greenLaserNumber = 0;
	public int maxGreenLaserNumber = 0;
	private int nukeMeter;
	private int invisible;
	private boolean maxSpeedAttained;

	//showLevel is not germane to the Falcon. Rather, it controls whether the level is shown in the middle of the
	// screen. However, given that the Falcon reference is never null, and that a Falcon is a Movable whose move/draw
	// methods are being called every ~40ms, this is a very convenient place to store this variable.
	private int showLevel;
	private boolean thrusting;
	//enum used for turnState field
	public enum TurnState {IDLE, LEFT, RIGHT, UP, DOWN}
	private TurnState turnState = TurnState.IDLE;


	// ==============================================================
	// CONSTRUCTOR
	// ==============================================================
	
	public Falcon() {

		setTeam(Team.FRIEND);
		maxGreenLaserNumber = 0;
		setRadius(MIN_RADIUS);


		//We use HashMap which has a seek-time of O(1)
		//See the resources directory in the root of this project for pngs.
		//Using enums as keys is safer b/c we know the value exists when we reference the consts later in code.
    	Map<ImageState, BufferedImage> rasterMap = new HashMap<>();
		rasterMap.put(ImageState.FALCON_INVISIBLE, null );
		rasterMap.put(ImageState.FALCON, loadGraphic("/imgs/playerShip/playerShip1_blue.png") ); //normal ship
		rasterMap.put(ImageState.FALCON_THR, loadGraphic("/imgs/playerShip/playerShip1_blue.png") ); //normal ship thrusting
		rasterMap.put(ImageState.FALCON_PRO, loadGraphic("/imgs/playerShip/playerShip1_blue.png") ); //protected ship (green)
		rasterMap.put(ImageState.FALCON_PRO_THR, loadGraphic("/imgs/playerShip/playerShip1_blue.png") ); //green thrusting

		setRasterMap(rasterMap);


	}

	@Override
	public boolean isProtected() {
		return isProtected;
	}

	// ==============================================================
	// METHODS 
	// ==============================================================
	@Override
	public void move() {
		super.move();

		if (invisible > 0) invisible--;
//		if (shield > 0) shield--;
		if (nukeMeter > 0) nukeMeter--;
		//The falcon is a convenient place to decrement the showLevel variable as the falcon
		//move() method is being called every frame (~40ms); and the falcon reference is never null.
		if (showLevel > 0) showLevel--;

		final double THRUST = 0.85;
		final int MAX_VELOCITY = 39;

		final int MY_SLOW_VELOCITY = 2;

		// I want to create my own moving logic here, which is simple:

		// the head of the plane will always face up
		setOrientation(0);

		// use up, down, left, right to control the movement of the plane, firing will not make the plane backward


		switch (turnState){
			case LEFT:
				setDeltaX(getDeltaX() - MY_SLOW_VELOCITY);
				break;
			case RIGHT:
				setDeltaX(getDeltaX() + MY_SLOW_VELOCITY);
				break;
			case UP:
				setDeltaY(getDeltaY() - MY_SLOW_VELOCITY);
				break;
			case DOWN:
				setDeltaY(getDeltaY() + MY_SLOW_VELOCITY);
				break;
			case IDLE:
				setDeltaX(0);
				setDeltaY(0);
			default:
				//do nothing
				break;
		}





//		//apply some thrust vectors using trig.
//		if (thrusting) {
//			double vectorX = Math.cos(Math.toRadians(getOrientation()))
//					* THRUST;
//			double vectorY = Math.sin(Math.toRadians(getOrientation()))
//					* THRUST;
//
//			//Absolute velocity is the hypotenuse of deltaX and deltaY
//			int absVelocity =
//					(int) Math.sqrt(Math.pow(getDeltaX()+ vectorX, 2) + Math.pow(getDeltaY() + vectorY, 2));
//
//			//only accelerate (or adjust radius) if we are below the maximum absVelocity.
//			if (absVelocity < MAX_VELOCITY){
//				//accelerate
//				setDeltaX(getDeltaX() + vectorX);
//				setDeltaY(getDeltaY() + vectorY);
//				//Make the ship radius bigger when the absolute velocity increases, thereby increasing difficulty when not
//				// protected, and allowing player to use the shield offensively when protected.
//				setRadius(MIN_RADIUS + absVelocity / 3);
//				maxSpeedAttained = false;
//			} else {
//				//at max speed, you will lose steerage if you attempt to accelerate in the same general direction
//				//show WARNING message to player using this flag (see drawFalconStatus() of GamePanel class)
//				maxSpeedAttained = true;
//			}
//
//		}
//
//		//adjust the orientation given turnState
//		int adjustOr = getOrientation();
//		switch (turnState){
//			case LEFT:
//				adjustOr = getOrientation() <= 0 ? 360 - TURN_STEP : getOrientation() - TURN_STEP;
//				break;
//			case RIGHT:
//				adjustOr = getOrientation() >= 360 ? TURN_STEP : getOrientation() + TURN_STEP;
//				break;
//			case IDLE:
//			default:
//				//do nothing
//		}
//		setOrientation(adjustOr);

	}








	//Since the superclass Spite does not provide an
	// implementation for draw() (contract method from Movable) ,we inherit that contract debt, and therefore must
	// provide an implementation. This is a raster and vector (see drawShield below) implementation of draw().
	@Override
	public void draw(Graphics g) {

		//set local image-state
		ImageState imageState;
		if (invisible > 0){
			imageState = ImageState.FALCON_INVISIBLE;
		}
		else if (isProtected()){
			imageState = thrusting ? ImageState.FALCON_PRO_THR : ImageState.FALCON_PRO;
			//you can also combine vector elements and raster elements
		    drawShield(g);
		}
		else { //not protected
			imageState = thrusting ? ImageState.FALCON_THR : ImageState.FALCON;
		}

		//down-cast (widen the aperture of) the graphics object to gain access to methods of Graphics2D
		//and render the raster image according to the image-state
		renderPlayerPlaneRaster((Graphics2D) g, getRasterMap().get(imageState));

	}

	private void drawShield(Graphics g){
		g.setColor(Color.CYAN);
		g.drawOval(getCenter().x - getRadius(), getCenter().y - getRadius(), getRadius() *2, getRadius() *2);
	}

	private void renderPlayerPlaneRaster(Graphics2D g2d, BufferedImage bufferedImage) {
		if (bufferedImage == null) return;

		// Custom rendering logic for LaserBlue
		// This might involve different scaling, positioning, or other graphical adjustments
		// specific to the laser's appearance and behavior

		// Example: Simple rendering without scaling
		g2d.drawImage(bufferedImage, getCenter().x - bufferedImage.getWidth() / 2,
				getCenter().y - bufferedImage.getHeight() / 2, null);
	}


} //end class
