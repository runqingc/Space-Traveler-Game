package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

public abstract class EnemyShip extends Sprite{

    public int health;

    long lastFireTime;

    public static long SHOOTING_INTERVAL = Game.FRAMES_PER_SECOND * 2;


    public EnemyShip(){
        setTeam(Team.FOE);
    }

}
