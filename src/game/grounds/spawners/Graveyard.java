package game.grounds.spawners;

import game.actors.npc.enemies.Enemy;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends EnemySpawner {

    public Graveyard(Enemy enemy) {
        super(enemy);
        setDisplayChar('n');
    }

}
