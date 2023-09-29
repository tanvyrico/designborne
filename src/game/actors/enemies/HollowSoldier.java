package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.consumables.Runes;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;

import java.util.Random;

/**
 * A class representing a Hollow Soldier enemy in the game.
 * Hollow Soldiers are hostile enemies that can attack Player actors and drop items upon defeat.
 */
public class HollowSoldier extends Enemy {
    private final int intrinsicDamage = 50;
    private final double spawnRate = 0.1;

    /**
     * Constructor for the HollowSoldier class.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.getIntrinsicWeapon();
        this.addBalance(100);
    }

    /**
     * Spawns a HollowSoldier.
     *
     * @return A new instance of the HollowSoldier enemy.
     */
    @Override
    public HollowSoldier spawnEnemy() {
        return new HollowSoldier();
    }

    /**
     * Retrieves the spawn rate of the Hollow Soldier.
     *
     * @return The spawn rate of the Hollow Soldier.
     */
    @Override
    public double getSpawnRate(){return this.spawnRate;}

    /**
     * Retrieves the intrinsic weapon used by the Hollow Soldier.
     *
     * @return An IntrinsicWeapon representing the Hollow Soldier's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "slashes");
    }

    /**
     * Handles the outcome when the Hollow Soldier becomes unconscious.
     * Drops healing vial and refreshing flask upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Hollow Soldier.
     * @param map   The GameMap where the Hollow Soldier was defeated.
     * @return A message describing the outcome of the Hollow Soldier's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 0.2) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        if (random.nextDouble() <= 0.3) {
            RefreshingFlask refreshingFlask = new RefreshingFlask();
            location.addItem(refreshingFlask);
        }

        location.addItem(new Runes(this.getBalance()));

        return this + " met their demise at the hands of " + actor;
    }
}
