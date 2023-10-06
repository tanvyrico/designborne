package game.grounds.spawners;

import game.actors.npcs.enemies.Enemy;

/**
 * Class representing a hut ground on the game map, where enemies can spawn.
 * @author Willson Louisse HV
 * Modified by: Group6
 */
public class Hut extends EnemySpawner{

    /**
     * Constructor for the Hut class.
     *
     * @param enemy The type of enemy to be spawned in the hut.
     */
    public Hut(Enemy enemy){
        super(enemy);
        setDisplayChar('h');
    }
}
