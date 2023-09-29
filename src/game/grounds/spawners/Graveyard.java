package game.grounds.spawners;

import game.actors.enemies.Enemy;


/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends EnemySpawner {

    /**
     * Constructor for the Graveyard class.
     *
     * @param enemy     The type of enemy that can spawn in the graveyard.
     */
    public Graveyard(Enemy enemy) {
        super(enemy);
        setDisplayChar('n');
    }

}
