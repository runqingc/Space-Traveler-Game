package edu.uchicago.gerber._08final.mvc.controller;



import edu.uchicago.gerber._08final.mvc.model.*;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//The CommandCenter is a singleton that manages the state of the game.
//the lombok @Data gives us automatic getters and setters on all members
@Data
public class CommandCenter {

	public  int numFalcons;
	public int numStar;
	public int maxStar = 15;
	private int numBoss;
	private EnemyBOSS enemyBOSS = null;
	private HintArrowControl mainHint = null;
	private  int level;
	private  long score;
	private  boolean paused;

	//if the number of falcons is zero, then game over
	//		return numFalcons < 1;
	@Getter
	private boolean gameOver = true;

	private  boolean muted = false;

	//this value is used to count the number of frames (full animation cycles) in the game
	private long frame;

	//the falcon is located in the movFriends list, but since we use this reference a lot, we keep track of it in a
	//separate reference. Use final to ensure that the falcon ref always points to the single falcon object on heap.
	//Lombok will not provide setter methods on final members
	private final Falcon falcon  = new Falcon();

	//lists containing our movables subdivided by team
	private final List<Movable> movBackgrounds = new LinkedList<>();
	private final List<Movable> movForegrounds = new LinkedList<>();
	private final List<Movable> movDebris = new LinkedList<>();
	private final List<Movable> movFriends = new LinkedList<>();
	private final List<Movable> movFoes = new LinkedList<>();
	private final List<Movable> movFloaters = new LinkedList<>();

	private final GameOpsQueue opsQueue = new GameOpsQueue();

	//for sound playing. Limit the number of threads to 5 at a time.
	private final ThreadPoolExecutor soundExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

	//singleton
	private static CommandCenter instance = null;

	// Constructor made private
	private CommandCenter() {}

    //this class maintains game state - make this a singleton.
	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}


	public void initGame(){
		clearAll();
		setGameOver(false);
		generateStarField();
		setLevel(0);
		setScore(0);
		setPaused(false);
		//set to one greater than number of falcons lives in your game as initFalconAndDecrementNum() also decrements
		setNumFalcons(5);
		System.out.println("numFalcons0: "+numFalcons);
		setNumStar(0);
		setNumBoss(0);
		setEnemyBOSS(null);
		initFalconAndDecrementFalconNum();
//		setMuted(false);
		//add the falcon to the movFriends list
		opsQueue.enqueue(falcon, GameOp.Action.ADD);
		for (int i = 0; i < 150; i++) {  // Adjust number of stars as needed
			opsQueue.enqueue(new BackgroundStar(), GameOp.Action.ADD);
		}


	}

	private void generateStarField(){

		int count = 100;
		while (count-- > 0){
			opsQueue.enqueue(new Star(), GameOp.Action.ADD);
		}

	}




	public void initFalconAndDecrementFalconNum(){
		numFalcons--;


//		if (isGameOver()) return;
		Sound.playSound("shipspawn.wav");
//		falcon.setShield(Falcon.INITIAL_SPAWN_TIME);
		falcon.setInvisible(Falcon.INITIAL_SPAWN_TIME/4);
		// set initial laser level
		falcon.setLaserLevel(1);
		falcon.setRedLaserLevel(0);
		falcon.setMaxGreenLaserNumber(2);
		CommandCenter.getInstance().getOpsQueue().enqueue(new Shield2(CommandCenter.getInstance().getFalcon()), GameOp.Action.ADD);
		//put falcon in the middle of the game-space
		falcon.setCenter(new Point(Game.DIM.width / 2, Game.DIM.height / 5 *4));
		//random number between 0-360 in steps of TURN_STEP
		falcon.setOrientation(Game.R.nextInt(360 / Falcon.TURN_STEP) * Falcon.TURN_STEP);
		falcon.setDeltaX(0);
		falcon.setDeltaY(0);
		falcon.setRadius(Falcon.MIN_RADIUS);
		falcon.setMaxSpeedAttained(false);
		falcon.setNukeMeter(0);
	}

	public void incrementFrame(){
		//use of ternary expression to simplify the logic to one line
		frame = frame < Long.MAX_VALUE ? frame + 1 : 0;
	}

	private void clearAll(){
		movBackgrounds.clear();
		movForegrounds.clear();
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
	}


}
