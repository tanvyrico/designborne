package game.grounds.spawners;

import game.actors.npcs.enemies.Enemy;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends EnemySpawner {

    /**
     * Constructor for the Graveyard class.
     *
     * @param enemy The type of enemy to be spawned in the graveyard.
     */
    public Graveyard(Enemy enemy) {
        super(enemy);
        setDisplayChar('n');
    }

}
