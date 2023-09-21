package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.OldKey;
import game.items.consumables.HealingVial;

import java.util.Random;

public class ForestKeeper extends Enemy {
    private final int intrinsicDamage = 25;
    private final double spawnRate = 0.15;

    public ForestKeeper(){
        super("Forest Keeper", '8', 125);
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
        return new IntrinsicWeapon(intrinsicDamage, "slashes", 75);
    }

    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 0.2) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        return this + " met their demise at the hands of " + actor;
    }
}
