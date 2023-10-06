package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * An Action representing a basic attack action performed by an actor using a weapon.
 * This action calculates the damage, checks for a hit, and updates the target's health accordingly.
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor for the AttackAction class with specified weapon.
     *
     * @param target     The actor to attack.
     * @param direction  The direction in which the attack is performed (for display).
     * @param weapon     The weapon used for the attack.
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor for the AttackAction class with intrinsic weapon (default).
     *
     * @param target     The actor to attack.
     * @param direction  The direction in which the attack is performed (for display).
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack action, calculating damage, checking for a hit, and updating the target's health.
     *
     * @param actor The actor performing the attack.
     * @param map   The GameMap where the attack takes place.
     * @return A description of the attack result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Provides a menu description for the attack action.
     *
     * @param actor The actor performing the attack.
     * @return A description of the menu option for this attack action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }

}
