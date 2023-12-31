package edu.uchicago.gerber._08final.mvc.controller;

import edu.uchicago.gerber._08final.mvc.model.*;
import edu.uchicago.gerber._08final.mvc.view.GamePanel;


import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;


import static edu.uchicago.gerber._08final.mvc.model.HintArrowControl.appeared;


// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game implements Runnable, KeyListener {

    // ===============================================
    // FIELDS
    // ===============================================

    public static final Dimension DIM = new Dimension(1100, 900); //the dimension of the game.
    private final GamePanel gamePanel;
    //this is used throughout many classes.
    public static final Random R = new Random();

    public final static int ANIMATION_DELAY = 40; // milliseconds between frames

    public final static int FRAMES_PER_SECOND = 1000 / ANIMATION_DELAY;

    private final Thread animationThread;


    //key-codes
    private static final int
            PAUSE = 80, // p key
            QUIT = 81, // q key
            LEFT = 37, // move left; left arrow
            RIGHT = 39, // move right; right arrow
            UP = 38, // move up; up arrow
            DOWN = 40, // move down; down arrow
            START = 83, // s key
            FIRE = 32, // space key
            MUTE = 77, // m-key mute

            NUKE = 78; // n-key mute

    // for possible future use
    // HYPER = 68, 					// D key
    //ALIEN = 65;                // A key
    // SPECIAL = 70; 					// fire special weapon;  F key

    private final Clip soundThrust;
    private Clip soundBackground;

    long lastRainTime = -1;

    // ===============================================
    // ==CONSTRUCTOR
    // ===============================================

    public Game() {

        gamePanel = new GamePanel(DIM);
        gamePanel.addKeyListener(this); //Game object implements KeyListener
        soundThrust = Sound.clipForLoopFactory("whitenoise.wav");
        soundBackground = Sound.clipForLoopFactory("bgm.wav");
        soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
        //fire up the animation thread
        animationThread = new Thread(this); // pass the animation thread a runnable object, the Game object
        animationThread.start();


    }

    // ===============================================
    // ==METHODS
    // ===============================================

    public static void main(String[] args) {
        //typical Swing application start; we pass EventQueue a Runnable object.
        EventQueue.invokeLater(Game::new);
    }

    // Game implements runnable, and must have run method
    @Override
    public void run() {

        // lower animation thread's priority, thereby yielding to the "main" aka 'Event Dispatch'
        // thread which listens to keystrokes
        animationThread.setPriority(Thread.MIN_PRIORITY);

        // and get the current time
        long startTime = System.currentTimeMillis();



        // this thread animates the scene
        while (Thread.currentThread() == animationThread) {


            //this call will cause all movables to move() and draw() themselves every ~40ms
            // see GamePanel class for details
            gamePanel.update(gamePanel.getGraphics());

            if(!CommandCenter.getInstance().isGameOver() && !CommandCenter.getInstance().isPaused()
                    && CommandCenter.getInstance().getLevel()<=5 ){
                // if boss dies and level up to 6, cease fire
                checkHint();
                if(CommandCenter.getInstance().getNumFalcons()>0){
                    checkBlueRain();
                    checkGreenRain();
                    checkFloaters();
                    checkFalconFire();
                }
                checkEnemyFire();
                checkFoes();
                checkCollisions();
            }



            //keep track of the frame for development purposes
            CommandCenter.getInstance().incrementFrame();

            // surround the sleep() in a try/catch block
            // this simply controls delay time between
            // the frames of the animation
            try {
                // The total amount of time is guaranteed to be at least ANIMATION_DELAY long.  If processing (update)
                // between frames takes longer than ANIMATION_DELAY, then the difference between startTime -
                // System.currentTimeMillis() will be negative, then zero will be the sleep time
                startTime += ANIMATION_DELAY;

                Thread.sleep(Math.max(0,
                        startTime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                // do nothing (bury the exception), and just continue, e.g. skip this frame -- no big deal
            }
        } // end while
    } // end run

    private void checkHint(){

        if(HintArrowControl.index<=2 && (CommandCenter.getInstance().getMainHint()==null ||CommandCenter.getInstance().getMainHint().getCenter().y>=300)){
            HintArrowControl thisHint = new HintArrowControl();
            CommandCenter.getInstance().getOpsQueue().enqueue(thisHint, GameOp.Action.ADD);
            CommandCenter.getInstance().setMainHint(thisHint);
            HintArrowControl.index++;
        }

    }


    private void checkBlueRain(){

        if(lastRainTime>0 && System.currentTimeMillis()-lastRainTime<=3000){
            for(int i=0; i<2 && System.currentTimeMillis()-lastRainTime<=3000; ++i){
                CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.RAIN), GameOp.Action.ADD);
            }
        }

    }

    private void checkGreenRain(){

        if(LaserGreenRain.POS<=Game.DIM.width/2){

            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserGreenRain(Game.DIM.width/2 + LaserGreenRain.POS),  GameOp.Action.ADD);
            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserGreenRain(Game.DIM.width/2 - LaserGreenRain.POS),  GameOp.Action.ADD);
            if(LaserGreenRain.POS+20>Game.DIM.width/2 && CommandCenter.getInstance().getLevel()<5){
                CommandCenter.getInstance().getOpsQueue().enqueue(new LevelUp(),  GameOp.Action.ADD);
            }
            LaserGreenRain.POS+=20;
        }

    }



    // controls the foe spawning logic here
    private void checkFoes(){

        int level = CommandCenter.getInstance().getLevel();
        if(level>5) return;
        // traverse all kinds of foes
        if ((Asteroid.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % Asteroid.SPAWN_TIME[level] == 0) {
            spawnBigAsteroids(1);
        }
        if ((EnemyBlack1.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyBlack1.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyBlack1(), GameOp.Action.ADD);
        }
        if ((EnemyBlack2.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyBlack2.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyBlack2(), GameOp.Action.ADD);
        }
        if ((EnemyBlack3.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyBlack3.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyBlack3(), GameOp.Action.ADD);
        }
        if ((EnemyBlack4.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyBlack4.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyBlack4(), GameOp.Action.ADD);
        }
        if ((EnemyBlack5.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyBlack5.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyBlack5(), GameOp.Action.ADD);
        }
        if ((EnemyYellowUFO.SPAWN_TIME[level]>0) && CommandCenter.getInstance().getFrame() % EnemyYellowUFO.SPAWN_TIME[level] == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new EnemyYellowUFO(), GameOp.Action.ADD);
        }

        if(CommandCenter.getInstance().getLevel()==5 && CommandCenter.getInstance().getNumBoss()<1){
            EnemyBOSS enemyBOSS = new EnemyBOSS();
            CommandCenter.getInstance().getOpsQueue().enqueue(enemyBOSS, GameOp.Action.ADD);
            CommandCenter.getInstance().setNumBoss(1);
            CommandCenter.getInstance().setEnemyBOSS(enemyBOSS);
            stopLoopingSounds(soundBackground);
            soundBackground = Sound.clipForLoopFactory("reduced_boss.wav");
            soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
        }


    }


    private void checkFloaters() {

//        spawnNewWallFloater();
        spawnShieldFloater();
//        spawnNukeFloater();
        spawnBoltFloater();
        spawnRedBoltFloater();
        spawnGreenBoltFloater();
        spawnStarFloater();
        spawnHeartFloater();
        spawnStarGoldFloater();
    }

    private void checkFalconFire(){
        spawnBlueLaser();
        spawnRedLaser();
        spawnGreenLaser();
    }

    private void checkEnemyFire(){
        // for every enemy ship, check floater
        for(Movable movFoe : CommandCenter.getInstance().getMovFoes()){
            if(movFoe instanceof EnemyBlack2){
                if (CommandCenter.getInstance().getFrame() % EnemyBlack2.SHOOTING_INTERVAL == 0) {
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                }
            }else if(movFoe instanceof EnemyBlack1){
                if (CommandCenter.getInstance().getFrame() % EnemyBlack1.SHOOTING_INTERVAL == 0){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SE), GameOp.Action.ADD);
                }
            }else if(movFoe instanceof EnemyBlack5){
                EnemyBlack5 enemyBlack5 = (EnemyBlack5) movFoe;
                if (CommandCenter.getInstance().getFrame() % EnemyBlack5.SHOOTING_INTERVAL == 0){
                    if(enemyBlack5.lastFirePosition==1){
                        enemyBlack5.lastFirePosition--;

                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NE), GameOp.Action.ADD);
                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NW), GameOp.Action.ADD);

                    }else{
                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.N), GameOp.Action.ADD);
                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SW), GameOp.Action.ADD);
                        CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SE), GameOp.Action.ADD);
                        enemyBlack5.lastFirePosition++;

                    }
                }
            } else if (movFoe instanceof EnemyYellowUFO) {
                EnemyYellowUFO enemyYellowUFO = (EnemyYellowUFO) movFoe;
                if (CommandCenter.getInstance().getFrame() % EnemyYellowUFO.SHOOTING_INTERVAL == 0){
                    enemyYellowUFO.lastFirePosition %= 8;
                    switch (enemyYellowUFO.lastFirePosition) {
                        case 0:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                            break;
                        case 1:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SW), GameOp.Action.ADD);
                            break;
                        case 2:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.W), GameOp.Action.ADD);
                            break;
                        case 3:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NW), GameOp.Action.ADD);
                            break;
                        case 4:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.N), GameOp.Action.ADD);
                            break;
                        case 5:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NE), GameOp.Action.ADD);
                            break;
                        case 6:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.E), GameOp.Action.ADD);
                            break;
                        case 7:
                            CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SE), GameOp.Action.ADD);
                            break;
                        default:
                            // This should not happen, but it's here as a safeguard
                            break;
                    }
                    // Increment and wrap around the last fire position
                    enemyYellowUFO.lastFirePosition = (enemyYellowUFO.lastFirePosition + 1) % 8;

                }
            }else if(movFoe instanceof EnemyBlack4){
                if (CommandCenter.getInstance().getFrame() % EnemyBlack4.SHOOTING_INTERVAL == 0){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SE), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.E), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NE), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.N), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.NW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.W), GameOp.Action.ADD);
                }
            } else if (movFoe instanceof EnemyBOSS) {
                if(CommandCenter.getInstance().getFrame() % EnemyBOSS.SHOOTING_INTERVAL == 0){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.S), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SSW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SWW), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SE), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SSE), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.E), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.SEE), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new GreyBullet02((EnemyShip) movFoe, GreyBullet02.BulletType.W), GameOp.Action.ADD);
                }
            }


        }
    }




    private void checkCollisions() {

        Point pntFriendCenter, pntFoeCenter;
        int radFriend, radFoe;




        //This has order-of-growth of O(n^2), there is no way around this.
        for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
            for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {

                pntFriendCenter = movFriend.getCenter();
                pntFoeCenter = movFoe.getCenter();
                radFriend = movFriend.getRadius();
                radFoe = movFoe.getRadius();

                //detect collision
                // BOSS has special shape, so it will be dealt with separately
                if (pntFriendCenter.distance(pntFoeCenter) < (radFriend + radFoe)

                        || (movFoe instanceof EnemyBOSS && Math.abs(movFriend.getCenter().y-CommandCenter.getInstance().getEnemyBOSS().getCenter().y)<=25
                        && Math.abs(movFriend.getCenter().x-CommandCenter.getInstance().getEnemyBOSS().getCenter().x)<=310)

                ) {
                    if(movFoe instanceof Asteroid){
                        CommandCenter.getInstance().getOpsQueue().enqueue(movFoe, GameOp.Action.REMOVE);
                    }

                    //remove the friend (so long as he is not protected)
                    if (!movFriend.isProtected()) {
                        CommandCenter.getInstance().getOpsQueue().enqueue(movFriend, GameOp.Action.REMOVE);
                        if(movFriend instanceof LaserBlue){
                            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlueDebris((Sprite) movFriend), GameOp.Action.ADD);
                        }
                        if(movFriend instanceof LaserRed){
                            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserRedDebris((Sprite) movFriend), GameOp.Action.ADD);
                            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserRedRange((Sprite) movFriend), GameOp.Action.ADD);
                            // apply range damage
                        }
                        if(movFriend instanceof LaserGreen){
                            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserGreenDebris((Sprite) movFriend), GameOp.Action.ADD);
                            CommandCenter.getInstance().getFalcon().greenLaserNumber--;
                        }
                        if(movFriend instanceof LaserGreenRain){
                            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserGreenDebris((Sprite) movFriend), GameOp.Action.ADD);
                        }


                        //remove the foe on some occasion
                        // Inside your collision detection logic
                        if (movFoe instanceof EnemyShip) {
                            EnemyShip enemyShip = (EnemyShip) movFoe;
                            if (movFriend instanceof LaserBlue) {
                                handleLaserEnemyCollision((LaserBlue) movFriend, enemyShip);
                            } else if (movFriend instanceof LaserRed) {
                                handleLaserEnemyCollision((LaserRed) movFriend, enemyShip);
                            } else if (movFriend instanceof LaserRedRange) {
                                handleLaserEnemyCollision((LaserRedRange) movFriend, enemyShip);
                            } else if (movFriend instanceof LaserGreen) {
                                handleLaserEnemyCollision((LaserGreen) movFriend, enemyShip);
                            } else if (movFriend instanceof LaserGreenRain) {
                                handleLaserEnemyCollision((LaserGreenRain) movFriend, enemyShip);
                            }
                            // ... Add other laser types if needed
                        }


                    }
                    // else: is friend is protected, do nothing




                    if (movFoe instanceof Brick) {
                        CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore() + 1000);
                        Sound.playSound("rock.wav");
                    } else {
                        CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore() + 10);
                        Sound.playSound("reduced_kapow.wav");
                    }
                }

            }//end inner for
        }//end outer for

        //check for collisions between falcon and floaters. Order of growth of O(n) where n is number of floaters
        Point pntFalCenter = CommandCenter.getInstance().getFalcon().getCenter();
        int radFalcon = CommandCenter.getInstance().getFalcon().getRadius();

        Point pntFloaterCenter;
        int radFloater;
        for (Movable movFloater : CommandCenter.getInstance().getMovFloaters()) {
            pntFloaterCenter = movFloater.getCenter();
            radFloater = movFloater.getRadius();

            //detect collision
            if (pntFalCenter.distance(pntFloaterCenter) < (radFalcon + radFloater)) {

                Class<? extends Movable> clazz = movFloater.getClass();
                switch (clazz.getSimpleName()) {
                    case "ShieldFloater":
                        Sound.playSound("shieldup.wav");
//                        CommandCenter.getInstance().getFalcon().setShield(Falcon.MAX_SHIELD);
                        CommandCenter.getInstance().getOpsQueue().enqueue(new Shield2(CommandCenter.getInstance().getFalcon()), GameOp.Action.ADD);
                        break;
                    case "NewWallFloater":
                        Sound.playSound("insect.wav");
                        buildWall();
                        break;
                    case "NukeFloater":
                        Sound.playSound("nuke-up.wav");
                        CommandCenter.getInstance().getFalcon().setNukeMeter(Falcon.MAX_NUKE);
                        break;
                    case "BoltFloater":
                        // haven't chosen sound yet
                        CommandCenter.getInstance().getFalcon().setLaserLevel(CommandCenter.getInstance().getFalcon().getLaserLevel()+1);
                        Sound.playSound("energy-3-107098.wav");
                        break;
                    case "RedBoltFloater":
                        CommandCenter.getInstance().getFalcon().setRedLaserLevel(CommandCenter.getInstance().getFalcon().getLaserLevel()+1);
                        Sound.playSound("energy-3-107098.wav");
                        break;
                    case "StarFloater":
                        lastRainTime = System.currentTimeMillis();
                        Sound.playSound("energy-3-107098.wav");
                        break;
                    case "HeartFloater":
                        CommandCenter.getInstance().numFalcons++;
                        Sound.playSound("health_pickup.wav");
                        break;
                    case "GreenBoltFloater":
                        CommandCenter.getInstance().getFalcon().maxGreenLaserNumber+=2;
                        Sound.playSound("energy-3-107098.wav.wav");
                        break;
                    case "StarGold":
                        if(CommandCenter.getInstance().numStar<CommandCenter.getInstance().maxStar)
                            CommandCenter.getInstance().setNumStar(CommandCenter.getInstance().numStar+1);
                        Sound.playSound("energy-1-107099.wav");
                        break;
                        default:
                        break;
                }
                CommandCenter.getInstance().getOpsQueue().enqueue(movFloater, GameOp.Action.REMOVE);
            }//end if
        }//end for

        processGameOpsQueue();

    }//end meth


    private void handleLaserEnemyCollision(Laser laser, EnemyShip enemyShip) {
        enemyShip.health -= laser.DAMAGE;
        if (enemyShip.health <= 0) {
            // boss dying
            if(enemyShip instanceof EnemyBOSS){
                // instantly kill all the other foes
                EnemyBOSS enemyBOSS = (EnemyBOSS) enemyShip;
                for (Movable movFoe : CommandCenter.getInstance().getMovFoes()){
                    if(movFoe instanceof EnemyShip && !(movFoe instanceof EnemyBOSS)){
                        EnemyShip enemyShip1 =  (EnemyShip)movFoe;
                        enemyShip1.health=0;
                        CommandCenter.getInstance().getOpsQueue().enqueue(enemyShip, GameOp.Action.REMOVE);
                        System.out.println("removed: "+movFoe.getClass());
                        CommandCenter.getInstance().getOpsQueue().enqueue(new RedCloudDebris(enemyShip), GameOp.Action.ADD);
                    }
                }
                // level up so that no foes spawning
                CommandCenter.getInstance().setLevel(6);
                CommandCenter.getInstance().setNumBoss(0);

                // protect the ship
                Sound.playSound("shieldup.wav");
                CommandCenter.getInstance().getOpsQueue().enqueue(new Shield2(CommandCenter.getInstance().getFalcon()), GameOp.Action.ADD);

                // multiple explosion around the boss
                for(int i=0; i<5; ++i){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new RedCloudDebris(enemyBOSS), GameOp.Action.ADD);
                }

                CommandCenter.getInstance().getMovFoes().clear();

                // add logic to victory
                CommandCenter.getInstance().getOpsQueue().enqueue(new Victory(), GameOp.Action.ADD);

                // switch to victory sound
                stopLoopingSounds(soundBackground);
                soundBackground = Sound.clipForLoopFactory("victory.wav");
                soundBackground.loop(Clip.LOOP_CONTINUOUSLY);


            }else{
                // regular enemy ship dying
                CommandCenter.getInstance().getOpsQueue().enqueue(enemyShip, GameOp.Action.REMOVE);
                System.out.println("removed: "+enemyShip.getClass());
                CommandCenter.getInstance().getOpsQueue().enqueue(new RedCloudDebris(enemyShip), GameOp.Action.ADD);

            }


        }
    }


    //This method adds and removes movables to/from their respective linked-lists.
    //This method is being called by the animationThread. The entire method is locked on the intrinsic lock of this
    // Game object. The main (Swing) thread also has access to the GameOpsQueue via the
    // key event methods such as keyReleased. Therefore, to avoid mutating the GameOpsQueue while we are iterating
    // it, we also synchronize the critical sections of the keyReleased and keyPressed methods below on the same
    // intrinsic lock.
    private synchronized void processGameOpsQueue() {

        //deferred mutation: these operations are done AFTER we have completed our collision detection to avoid
        // mutating the movable linkedlists while iterating them above.
        while (!CommandCenter.getInstance().getOpsQueue().isEmpty()) {
            GameOp gameOp = CommandCenter.getInstance().getOpsQueue().dequeue();
            Movable mov = gameOp.getMovable();
            GameOp.Action action = gameOp.getAction();

            switch (mov.getTeam()) {
                case FOE:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovFoes().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        CommandCenter.getInstance().getMovFoes().remove(mov);
                        if (mov instanceof Asteroid) spawnSmallerAsteroidsOrDebris((Asteroid) mov);
                        if (mov instanceof EnemyShip){
                            EnemyShip enemyShip = (EnemyShip) mov;
                            CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore()+enemyShip.health);
                            if(!(enemyShip instanceof GreyBullet02)){
                                CommandCenter.getInstance().getOpsQueue().enqueue(new StarGold(enemyShip), GameOp.Action.ADD);
                            }
                        }


                    }

                    break;
                case FRIEND:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovFriends().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        if (mov instanceof Falcon) {

                            // init falcon if there's still some
                            if(CommandCenter.getInstance().getNumFalcons()<=1){
                                CommandCenter.getInstance().getMovFriends().remove(mov);
                                // enqueue defeat sign
                                System.out.println("num falcon:" +CommandCenter.getInstance().getNumFalcons());
                                CommandCenter.getInstance().setNumFalcons(0);
                                CommandCenter.getInstance().getOpsQueue().enqueue(new Defeat(), GameOp.Action.ADD);
                                stopLoopingSounds(soundBackground);
                                Sound.playSound("lose.wav");


                            }else{
                                CommandCenter.getInstance().initFalconAndDecrementFalconNum();
                            }


                        } else {
                            CommandCenter.getInstance().getMovFriends().remove(mov);
                        }
                    }
                    break;

                case FLOATER:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovFloaters().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        CommandCenter.getInstance().getMovFloaters().remove(mov);
                    }
                    break;

                case DEBRIS:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovDebris().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        CommandCenter.getInstance().getMovDebris().remove(mov);
                    }
                    break;
                case BACKGROUND:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovBackgrounds().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        CommandCenter.getInstance().getMovBackgrounds().remove(mov);
                    }
                    break;
                case FOREGROUND:
                    if (action == GameOp.Action.ADD) {
                        CommandCenter.getInstance().getMovForegrounds().add(mov);
                    } else { //GameOp.Operation.REMOVE
                        CommandCenter.getInstance().getMovForegrounds().remove(mov);
                    }
                    break;
            }

        }
    }

    //shows how to add walls or rectangular elements one brick at a time
    private void buildWall() {
        final int BRICK_SIZE = Game.DIM.width / 30, ROWS = 2, COLS = 20, X_OFFSET = BRICK_SIZE * 5, Y_OFFSET = 50;

        for (int nCol = 0; nCol < COLS; nCol++) {
            for (int nRow = 0; nRow < ROWS; nRow++) {
                CommandCenter.getInstance().getOpsQueue().enqueue(
                        new Brick(
                                new Point(nCol * BRICK_SIZE + X_OFFSET, nRow * BRICK_SIZE + Y_OFFSET),
                                BRICK_SIZE),
                        GameOp.Action.ADD);

            }
        }
    }


    private void spawnNewWallFloater() {

        if (CommandCenter.getInstance().getFrame() % NewWallFloater.SPAWN_NEW_WALL_FLOATER == 0 && isBrickFree()) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new NewWallFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnShieldFloater() {

        if (CommandCenter.getInstance().getFrame() % ShieldFloater.SPAWN_SHIELD_FLOATER == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new ShieldFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnBoltFloater() {

        if (CommandCenter.getInstance().getFrame() % BoltFloater.SPAWN_BOLT_FLOATER == 0 && CommandCenter.getInstance().getFalcon().getLaserLevel()<3) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new BoltFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnRedBoltFloater(){

        if(CommandCenter.getInstance().getFrame() % RedBoltFloater.SPAWN_RED_BOLT_FLOATER==0 && CommandCenter.getInstance().getFalcon().getRedLaserLevel()==0){
            CommandCenter.getInstance().getOpsQueue().enqueue(new RedBoltFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnGreenBoltFloater(){
        if(CommandCenter.getInstance().getFrame() % GreenBoltFloater.SPAWN_GREEN_BOLT_FLOATER==0 && CommandCenter.getInstance().getFalcon().maxGreenLaserNumber<6){
            CommandCenter.getInstance().getOpsQueue().enqueue(new GreenBoltFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnStarFloater(){
        if (CommandCenter.getInstance().getFrame() % StarFloater.SPAWN_STAR_FLOATER == 0 ) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new StarFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnHeartFloater(){
        if(CommandCenter.getInstance().getFrame() % HeartFloater.SPAWN_HEART_FLOATER ==0 && CommandCenter.getInstance().numFalcons<5){
            CommandCenter.getInstance().getOpsQueue().enqueue(new HeartFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnStarGoldFloater(){
        if(CommandCenter.getInstance().getLevel()<=5 && CommandCenter.getInstance().getFrame() % StarGold.SPAWN_STAR_GOLD ==0 ){
            CommandCenter.getInstance().getOpsQueue().enqueue(new StarGold(), GameOp.Action.ADD);
        }
    }


    private void spawnNukeFloater() {

        if (CommandCenter.getInstance().getFrame() % NukeFloater.SPAWN_NUKE_FLOATER == 0) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new NukeFloater(), GameOp.Action.ADD);
        }
    }

    private void spawnRedLaser(){

        if(CommandCenter.getInstance().getFrame() % LaserRed.RED_FIRE_INTERVAL==0 && CommandCenter.getInstance().getFalcon().getRedLaserLevel()>0){
            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserRed(CommandCenter.getInstance().getFalcon(), LaserRed.LaserType.RIGHT_WING), GameOp.Action.ADD);
            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserRed(CommandCenter.getInstance().getFalcon(), LaserRed.LaserType.LEFT_WING), GameOp.Action.ADD);
        }

    }


    private void spawnGreenLaser(){

        if(CommandCenter.getInstance().getFrame()%LaserGreen.GREEN_FIRE_INTERVAL==0 && CommandCenter.getInstance().getFalcon().greenLaserNumber<=CommandCenter.getInstance().getFalcon().maxGreenLaserNumber){
            CommandCenter.getInstance().getOpsQueue().enqueue(new LaserGreen(CommandCenter.getInstance().getFalcon()), GameOp.Action.ADD);
        }

    }



    private void spawnBlueLaser(){

        if(CommandCenter.getInstance().getFrame()%LaserBlue.BLUE_FIRE_INTERVAL==0){
            synchronized (this){

                CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.MIDDLE), GameOp.Action.ADD);

                if(CommandCenter.getInstance().getFalcon().getLaserLevel()>=2){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.LEFT_WING), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.RIGHT_WING), GameOp.Action.ADD);
                }
                if(CommandCenter.getInstance().getFalcon().getLaserLevel()>=3){
                    CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.RIGHT), GameOp.Action.ADD);
                    CommandCenter.getInstance().getOpsQueue().enqueue(new LaserBlue(CommandCenter.getInstance().getFalcon(), LaserBlue.LaserType.LEFT), GameOp.Action.ADD);
                }
            }
            Sound.playSound("reduced_laser.wav");
        }

    }




    //this method spawns new Large (0) Asteroids
    private void spawnBigAsteroids(int num) {
        while (num-- > 0) {
            //Asteroids with size of zero are big
            CommandCenter.getInstance().getOpsQueue().enqueue(new Asteroid(0), GameOp.Action.ADD);

        }
    }

    private void spawnSmallerAsteroidsOrDebris(Asteroid originalAsteroid) {

        int size = originalAsteroid.getSize();
        //small asteroids
        if (size > 1) {
            CommandCenter.getInstance().getOpsQueue().enqueue(new WhiteCloudDebris(originalAsteroid), GameOp.Action.ADD);
        }
        //med and large
        else {
            //for large (0) and medium (1) sized Asteroids only, spawn 2 or 3 smaller asteroids respectively
            //We can use the existing variable (size) to do this
            size += 2;
            while (size-- > 0) {
                CommandCenter.getInstance().getOpsQueue().enqueue(new Asteroid(originalAsteroid), GameOp.Action.ADD);
            }
        }

    }

    private boolean isBrickFree() {
        //if there are no more Bricks on the screen
        boolean brickFree = true;
        for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
            if (movFoe instanceof Brick) {
                brickFree = false;
                break;
            }
        }
        return brickFree;
    }

    private boolean isLevelClear() {
        //if there are no more Asteroids on the screen
//        boolean asteroidFree = true;
//        for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
//            if (movFoe instanceof Asteroid) {
//                asteroidFree = false;
//                break;
//            }
//        }
//        return asteroidFree;

        // rewrite the logic to enter new level: gather 10 stars
        return CommandCenter.getInstance().numStar==CommandCenter.getInstance().maxStar;


    }

    private void checkNewLevel() {

        if (isLevelClear()) {
            //currentLevel will be zero at beginning of game
            int level = CommandCenter.getInstance().getLevel();
            //award some points for having cleared the previous level
            CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore() + (10_000L * level));
            Sound.playSound("success-1-6297.wav");
            // will not go to next level until the user pressed N


            //bump the level up
            if(level<5){
                level = level + 1;
                CommandCenter.getInstance().setLevel(level);
            }
            CommandCenter.getInstance().numStar = 0;



            // spawn green rain
            LaserGreenRain.POS = 0;


            //make falcon invincible momentarily in case new asteroids spawn on top of him, and give player
            //time to adjust to new asteroids in game space.
//            CommandCenter.getInstance().getFalcon().setShield(Falcon.INITIAL_SPAWN_TIME);
            //show "Level X" in middle of screen
            CommandCenter.getInstance().getFalcon().setShowLevel(Falcon.INITIAL_SPAWN_TIME);

        }
    }


    // Varargs for stopping looping-music-clips
    private static void stopLoopingSounds(Clip... clpClips) {
        Arrays.stream(clpClips).forEach(clip -> clip.stop());
    }

    // ===============================================
    // KEYLISTENER METHODS
    // ===============================================

    @Override
    public void keyPressed(KeyEvent e) {
        Falcon falcon = CommandCenter.getInstance().getFalcon();
        int keyCode = e.getKeyCode();

        if (keyCode == START && CommandCenter.getInstance().isGameOver()) {
            CommandCenter.getInstance().initGame();
            return;
        }


        switch (keyCode) {
            case PAUSE:
                CommandCenter.getInstance().setPaused(!CommandCenter.getInstance().isPaused());
                if (CommandCenter.getInstance().isPaused()) stopLoopingSounds(soundBackground, soundThrust);
                break;
            case QUIT:
                System.exit(0);
                break;
            case UP:
                falcon.setTurnState(Falcon.TurnState.UP);
//                falcon.setThrusting(true);
//                soundThrust.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            case DOWN:
                falcon.setTurnState(Falcon.TurnState.DOWN);
                break;
            case LEFT:
                falcon.setTurnState(Falcon.TurnState.LEFT);
                break;
            case RIGHT:
                falcon.setTurnState(Falcon.TurnState.RIGHT);
                break;


            // possible future use
            // case KILL:
            // case SHIELD:
            // case NUM_ENTER:

            default:
                break;
        }

    }

    //key events are triggered by the main (Swing) thread which is listening for keystrokes. Notice that some of the
    // cases below touch the GameOpsQueue such as fire bullet and nuke.
    //The animation-thread also has access to the GameOpsQueue via the processGameOpsQueue() method.
    // Therefore, to avoid mutating the GameOpsQueue on the main thread, while we are iterating it on the
    // animation-thread, we synchronize on the same intrinsic lock. processGameOpsQueue() is also synchronized.
    @Override
    public void keyReleased(KeyEvent e) {
        Falcon falcon = CommandCenter.getInstance().getFalcon();
        int keyCode = e.getKeyCode();
        //show the key-code in the console


        switch (keyCode) {
            case FIRE:
//                synchronized (this){
//                    CommandCenter.getInstance().getOpsQueue().enqueue(new Bullet(falcon), GameOp.Action.ADD);
//                }
//                Sound.playSound("thump.wav");
                checkNewLevel();
                break;
            case NUKE:

                // original logic to NUKE when pressed N
//                if (CommandCenter.getInstance().getFalcon().getNukeMeter() > 0){
//                    synchronized (this) {
//                        CommandCenter.getInstance().getOpsQueue().enqueue(new Nuke(falcon), GameOp.Action.ADD);
//                    }
//                    Sound.playSound("nuke.wav");
//                    CommandCenter.getInstance().getFalcon().setNukeMeter(0);
//                }

                // new logic to come to new game is N is pressed
                if(CommandCenter.getInstance().getNumFalcons()<=0 || CommandCenter.getInstance().getLevel()>=6){
                    CommandCenter.getInstance().setGameOver(true);
                    stopLoopingSounds(soundBackground);
                    soundBackground = Sound.clipForLoopFactory("bgm.wav");
                    soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
                }


                break;
            //releasing either the LEFT or RIGHT arrow key will set the TurnState to IDLE
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                falcon.setTurnState(Falcon.TurnState.IDLE);
                break;

//                falcon.setThrusting(false);
//                soundThrust.stop();
//                break;

            case MUTE:
                CommandCenter.getInstance().setMuted(!CommandCenter.getInstance().isMuted());
                if (!CommandCenter.getInstance().isMuted()) {
                    stopLoopingSounds(soundBackground);
                } else {
                    soundBackground.loop(Clip.LOOP_CONTINUOUSLY);
                }

                break;

            default:
                break;
        }

    }

    @Override
    // does nothing, but we need it b/c of KeyListener contract
    public void keyTyped(KeyEvent e) {
    }

}


