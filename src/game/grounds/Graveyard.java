package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

import java.util.Random;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends Ground {
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
        super('n');
        this.enemy = enemy;
        this.spawnRate = spawnRate;
    }

    /**
     * Checks if an enemy should spawn at the specified location in the graveyard and adds the enemy if appropriate.
     *
     * @param location The Location on the game map representing the graveyard.
     */
    public void tick(Location location) {
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (randomValue <= this.spawnRate) {
                location.addActor(enemy.spawnEnemy());
            }
        }
    }
}
