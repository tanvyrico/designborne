package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Ability;
import game.grounds.Gate;
import game.items.consumables.Runes;

/**
 * A class representing Abxervyer the Forest Watcher, a powerful enemy in the game.
 * This enemy has high health, an intrinsic weapon, and the ability to drop runes upon defeat.
 */
public class AbxervyerForestWatcher extends Enemy {

    private int intrinsicDamage = 80;

    private int hitRate = 25;

    /**
     * Constructor for the Enemy class.
     */
    public AbxervyerForestWatcher() {
        super("Abxervyer the Forest Watcher", 'Y', 2000);
        this.addCapability(Ability.VOID_INVINCIBILITY);
    }

    /**
     * Gets the spawn rate for this enemy.
     *
     * @return The spawn rate of this enemy, which is always 0.
     */
    @Override
    public double getSpawnRate(){
        return 0.0;
    }

    /**
     * Spawns a ForestKeeper.
     *
     * @return A new instance of the ForestKeeper enemy.
     */
    @Override
    public Enemy spawnEnemy() {
        return new AbxervyerForestWatcher();
    }


    /**
     * Retrieves the intrinsic weapon used by the Forest Keeper.
     *
     * @return An IntrinsicWeapon representing the Forest Keeper's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "WHACK", hitRate);
    }

    /**
     * Handles the outcome when the Forest Keeper becomes unconscious.
     * Drops healing vial upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Forest Keeper.
     * @param map   The GameMap where the Forest Keeper was defeated.
     * @return A message describing the outcome of the Forest Keeper's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        Gate gate = new Gate(map, location, "Congrats You've Defeated The Abxervyer");
        map.removeActor(this);

        location.setGround(gate);

        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }


}
