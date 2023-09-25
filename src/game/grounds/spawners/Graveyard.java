package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.grounds.spawners.EnemySpawner;

import java.util.Random;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends EnemySpawner {

    public Graveyard(Enemy enemy) {
        super(enemy);
        setDisplayChar('n');
    }

}
