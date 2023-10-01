package game.grounds.spawners;

import game.actors.npcs.enemies.Enemy;

public class Bush extends EnemySpawner {

    public Bush(Enemy enemy){
        super(enemy);
        setDisplayChar('m');
    }

}
