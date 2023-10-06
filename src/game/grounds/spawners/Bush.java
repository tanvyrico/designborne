package game.grounds.spawners;

import game.actors.npcs.enemies.Enemy;

/**
 * A specialized EnemySpawner representing a Bush.
 * Bushes can spawn enemies when interacted with.
 */
public class Bush extends EnemySpawner {

    /**
     * Constructor for the Bush class.
     *
     * @param enemy The enemy to be spawned from the bush.
     */
    public Bush(Enemy enemy){
        super(enemy);
        setDisplayChar('m');
    }
}
