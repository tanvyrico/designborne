package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;

import java.util.Random;

public abstract class EnemySpawner extends Ground {
    private Enemy enemy;
    private double spawnRate;
    public EnemySpawner(Enemy enemy, double spawnRate){
        super('s');
        this.enemy = enemy;
        this.spawnRate = spawnRate;
    }

    public void tick(Location location) {
        Random random = new Random();
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (randomValue <= this.spawnRate) {
                location.addActor(enemy.spawnEnemy());
            }
        }
    }
}
