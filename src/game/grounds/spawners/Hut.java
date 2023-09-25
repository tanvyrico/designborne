package game.grounds.spawners;

import game.actors.enemies.Enemy;

public class Hut extends EnemySpawner{
    public Hut(Enemy enemy){
        super(enemy);
        setDisplayChar('h');
    }
}
