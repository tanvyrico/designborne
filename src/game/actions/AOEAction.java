package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * An Action that allows the user of an allowed weapon to do an Area of Effect Attack.
 */
public class AOEAction extends Action {


    /**
     * An ArrayList that adds Actors that the Weapon User should attempt to hit.
     */
    private ArrayList<Actor> enemyActorsHitable = new ArrayList<>();

    /**
     * A weapon that will theoretically perform an AOE Attack if possible.
     */
    private Weapon weapon;
    /**
     * The direction where the weapon will attack first.
     */
    private String direction;

    private Actor target;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor
     * @param weaponItem for a Weapon that can do an Area of Effect Attack.
     * @param direction The direction that the weapon will attack first.
     *
     * The constructor will check if the Weapon is able to perform an AOE Attack by checking it's capabilities.
     * If it doesn't have, then an Area of Effect Action will not be performed.
     */

    public AOEAction(WeaponItem weaponItem, Actor target, String direction) {
        if (weaponItem.hasCapability(Status.AOE_POSSIBLE)) {
            this.weapon = weaponItem;
            this.target = target;
            this.direction = direction;
        }
    }

    /**
     * The "execute()" method here performs the Area of Effect Attack if the weapon can perform it.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String message indicating whether the AOE Attack was performed or not.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        int staminaNeeded = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.25);
        String result = "";
        Location user = map.locationOf(actor);
        if (actor.getAttribute(BaseActorAttributes.STAMINA)< staminaNeeded){
            return "No Stamina To Do AOE";
        }

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaNeeded);
        result += "\n" + new AttackAction(target,direction,weapon).execute(actor,map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        for (Exit exit: user.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor foundActor = destination.getActor();
                if (foundActor != target && !foundActor.hasCapability(Status.NON_HOSTILE)) {
                    enemyActorsHitable.add(foundActor);
                }
            }
        }

        for (Actor eachHostile: enemyActorsHitable) {
            boolean hitCheck = true;
            if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                System.out.println(actor + " misses " + eachHostile + ".");
                hitCheck = false;
            }

            if (hitCheck == true) {
                int damage = weapon.damage() / 2;
                result += "\n" + actor + " " + weapon.verb() + " " + eachHostile + " for " + damage + " damage.";
                eachHostile.hurt(damage);
                if (!eachHostile.isConscious()) {
                    result += "\n" + eachHostile.unconscious(eachHostile, map);
                }
            }
        }



        return actor + " has performed an Area of Effect Attack!" + "\n" + result;
    }

    /**
     * The "menuDescription()" method displays the option to the actor if they are in the vicinity of a hostile actor
     * and have capable weapon to perform the AOE Attack (depends on the circumstances and type of actor).
     * @param actor The actor performing the AOE action with their AOE-capable weapon.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " and then other " +
                " enemies with their unique/weapon's special skill in the direction of " +
                direction + " first.";
    }
}

