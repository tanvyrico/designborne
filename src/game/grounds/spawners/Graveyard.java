package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.grounds.spawners.EnemySpawner;

import java.util.Random;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends EnemySpawner {
    private Random random = new Random();
    private Enemy enemy;
    private double spawnRate;

    /**
     * Constructor for the Graveyard class.
     *
     * @param enemy     The type of enemy that can spawn in the graveyard.
     * @param spawnRate The probability of an enemy spawning when the tick method is called.
     */
    public Graveyard(Enemy enemy, double spawnRate) {
        super(enemy, spawnRate);
        setDisplayChar('n');
    }

}
