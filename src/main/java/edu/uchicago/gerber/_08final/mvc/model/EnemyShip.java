package edu.uchicago.gerber._08final.mvc.model;

public abstract class EnemyShip extends Sprite{

    public int health;

    public EnemyShip(){
        setTeam(Team.FOE);
    }

}
