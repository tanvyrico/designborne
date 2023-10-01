package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.enemies.Enemy;

import java.util.Random;

public abstract class EnemySpawner extends Ground {
    private Enemy enemy;

    public EnemySpawner(Enemy enemy){
        super('s');
        this.enemy = enemy;
    }

    public void tick(Location location) {
        Random random = new Random();
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (randomValue <= enemy.getSpawnRate()) {
                location.addActor(enemy.spawnEnemy());
            }
        }
    }
}
