package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

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
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }
}
