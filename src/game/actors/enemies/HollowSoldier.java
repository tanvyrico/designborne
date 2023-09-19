package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.AttackBehaviour;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.Status;

import java.util.Random;

/**
 * A class representing a Hollow Soldier enemy in the game.
 * Hollow Soldiers are hostile enemies that can attack Player actors and drop items upon defeat.
 */
public class HollowSoldier extends Enemy {
    private final int intrinsicDamage = 50;

    /**
     * Constructor for the HollowSoldier class.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.getIntrinsicWeapon();
    }



    /**
     * Spawns a WanderingUndead enemy as a replacement for the defeated Hollow Soldier.
     *
     * @return A new instance of the WanderingUndead enemy.
     */
    public HollowSoldier spawnEnemy() {
        return new HollowSoldier();
    }

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
     * Drops healing items or refreshing items upon defeat with a random chance.
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
            HealingVial healingVial = new HealingVial("Healing vial", 'a', true);
            location.addItem(healingVial);
        }

        if (random.nextDouble() <= 0.3) {
            RefreshingFlask refreshingFlask = new RefreshingFlask("Refreshing flask", 'u', true);
            location.addItem(refreshingFlask);
        }

        return this + " met their demise at the hands of " + actor;
    }
}
