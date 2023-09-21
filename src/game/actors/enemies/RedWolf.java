package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.consumables.HealingVial;

import java.util.Random;

public class RedWolf extends Enemy{
    private final int intrinsicDamage = 15;
    private final double spawnRate = 0.3;

    public RedWolf(){
        super("Red Wolf", 'r', 25);
        this.getIntrinsicWeapon();
        this.addCapability(Status.SPAWN_FROM_HUT);
    }

    public ForestKeeper spawnEnemy() {
        return new ForestKeeper();
    }

    public double getSpawnRate(){
        return this.spawnRate;
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "slashes", 80);
    }

    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 0.1) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        return this + " met their demise at the hands of " + actor;
    }
}
