package edu.uchicago.gerber._08final.mvc.model;

import edu.uchicago.gerber._08final.mvc.controller.Game;

public abstract class EnemyShip extends Sprite{

    public int health;

    public EnemyShip(){
        setTeam(Team.FOE);
    }

}
