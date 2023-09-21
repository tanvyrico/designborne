package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.enemies.Enemy;

import java.util.Random;

/**
 * Class representing a graveyard ground on the game map, where enemies can spawn.
 */
public class Graveyard extends Ground {
    private Random random = new Random();
    private Enemy enemy;

    /**
     * Constructor for the Graveyard class.
     *
     * @param enemy     The type of enemy that can spawn in the graveyard.
     */
    public Graveyard(Enemy enemy) {
        super('n');
        this.enemy = enemy;
    }

    /**
     * Checks if an enemy should spawn at the specified location in the graveyard and adds the enemy if appropriate.
     *
     * @param location The Location on the game map representing the graveyard.
     */
    public void tick(Location location) {
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (enemy.hasCapability(Status.SPAWN_FROM_GRAVEYARD) && randomValue <= this.enemy.getSpawnRate()) {
                location.addActor(enemy.spawnEnemy());
            }
        }
    }
}
