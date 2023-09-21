package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.enemies.Enemy;

import java.util.Random;

public class Bush extends Ground {
    private Random random = new Random();
    private Enemy enemy;

    public Bush(Enemy enemy) {
        super('m');
        this.enemy = enemy;
    }

    public void tick(Location location) {
        if (!location.containsAnActor()) {
            double randomValue = random.nextDouble();
            if (enemy.hasCapability(Status.SPAWN_FROM_BUSH) && randomValue <= this.enemy.getSpawnRate()) {
                location.addActor(enemy.spawnEnemy());
            }
        }
    }
}
