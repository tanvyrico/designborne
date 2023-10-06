package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.OldKey;
import game.items.consumables.HealingVial;
import game.items.consumables.Runes;

import java.util.Random;

/**
 * A class representing a Wandering Undead enemy in the game.
 * Wandering Undead are hostile enemies that can attack Player actors and drop items upon defeat.
 */
public class WanderingUndead extends Enemy {
    private final int intrinsicDamage = 30;
    private final double spawnRate = 0.25;

    /**
     * Constructor for the WanderingUndead class.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
        this.getIntrinsicWeapon();
        this.addBalance(50);
        this.setSpawnRate(0.25);
    }

    /**
     * Spawns a WanderingUndead.
     *
     * @return A new instance of the WanderingUndead enemy.
     */
    @Override
    public WanderingUndead spawnEnemy() {
        return new WanderingUndead();
    }

    /**
     * Retrieves the spawn rate of the Wandering Undead.
     *
     * @return The spawn rate of the Wandering Undead.
     */
    @Override
    public double getSpawnRate(){return this.spawnRate;}

    /**
     * Retrieves the intrinsic weapon used by the Wandering Undead.
     *
     * @return An IntrinsicWeapon representing the Wandering Undead's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "whacks");
    }

    /**
     * Handles the outcome when the Wandering Undead becomes unconscious.
     * Drops an old key or healing vial upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Wandering Undead.
     * @param map   The GameMap where the Wandering Undead was defeated.
     * @return A message describing the outcome of the Wandering Undead's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 1) {
            OldKey oldKey = new OldKey();
            location.addItem(oldKey);
        }

        if (random.nextDouble() <= 1) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        location.addItem(new Runes(this.getBalance()));

        return this + " met their demise at the hands of " + actor;
    }


}
