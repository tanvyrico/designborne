package game.grounds.spawners;

import game.actors.npc.enemies.Enemy;

public class Bush extends EnemySpawner {

    public Bush(Enemy enemy){
        super(enemy);
        setDisplayChar('m');
    }

}
