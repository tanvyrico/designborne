package game.grounds.spawners;

import game.actors.enemies.Enemy;
import game.grounds.spawners.EnemySpawner;

public class Bush extends EnemySpawner {

    public Bush(Enemy enemy, double spawnRate){
        super(enemy, spawnRate);
        setDisplayChar('m');
    }

}
