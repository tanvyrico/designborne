package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.enemies.Enemy;

import java.util.Random;

/**
 * An abstract class representing a ground element that can spawn enemies periodically.
 * Subclasses of this class should define the specific enemy type to spawn and the spawn rate.
 * @author Willson Louisse HV
 * Modified by: Group6
 */
public abstract class EnemySpawner extends Ground {
    private Enemy enemy;
    private Location location;

    /**
     * Constructor for the EnemySpawner class.
     *
     * @param enemy The type of enemy to be spawned by this spawner.
     */
    public EnemySpawner(Enemy enemy){
        super('s');
        this.enemy = enemy;
    }

    /**
     * Periodically checks if the location is empty and spawns the configured enemy based on spawn rate.
     *
     * @param location The location where the enemy may be spawned.
     */
    public void tick(Location location) {
        this.location = location;
        Random random = new Random();
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (randomValue <= this.enemy.getSpawnRate()) {
                location.addActor(enemy.spawnEnemy(location.map()));
            }
        }
    }

    public Location getLocation(){
        return this.location;
    }

}
