package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

import java.util.Random;

/**
 * An Action that allows the user of an allowed weapon to do an Area of Effect Attack.
 */
public class GreatSlamAction extends Action {

    /**
     * The multiplier applied to the weapon's damage for the AOE attack.
     */
    private final float AREA_DAMAGE_MULTIPLIER = 0.5f;

    /**
     * A weapon that will theoretically perform an AOE Attack if possible.
     */
    private WeaponItem weapon;

    /**
     * The direction where the weapon will attack first.
     */
    private String direction;

    /**
     * The target actor to attack.
     */
    private Actor target;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor
     *
     * @param weaponItem The weapon capable of performing an AOE attack.
     * @param target     The target actor to attack with the AOE.
     * @param direction  The direction in which the AOE attack will be executed.
     *
     * The constructor will check if the Weapon is able to perform an AOE Attack by checking it's capabilities.
     * If it doesn't have, then an Area of Effect Action will not be performed.
     */

    public GreatSlamAction(WeaponItem weaponItem, Actor target, String direction) {
        this.weapon = weaponItem;
        this.target = target;
        this.direction = direction;
    }

    /**
     * The "execute()" method here performs the Area of Effect Attack if the weapon can perform it.
     *
     * @param actor The actor performing the AOE attack.
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the outcome of the AOE attack.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        int staminaNeeded = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.05);
        String result = "";
        Location user = map.locationOf(actor);
        if (actor.getAttribute(BaseActorAttributes.STAMINA) >= staminaNeeded){
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaNeeded);
            result += new AttackAction(target,direction,weapon).execute(actor,map);

            this.weapon.updateDamageMultiplier(AREA_DAMAGE_MULTIPLIER);
            for (Exit exit: user.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor foundActor = destination.getActor();
                    if  (foundActor != target && (!foundActor.hasCapability(Status.HOSTILE_TO_ENEMY) && (foundActor.hasCapability(Status.FRIENDLY_TO_ENEMY)))){
                        result += "\n"+new AttackAction(foundActor,direction,weapon).execute(actor,map);
                    }
                }
            }
            this.weapon.updateDamageMultiplier(1f);
        }
        else {
            result += "Stamina is not enough to do Great Slam";
        }
        return result;
    }

    /**
     * The "menuDescription()" method displays the option to the actor if they are in the vicinity of a hostile actor
     * and have capable weapon to perform the AOE Attack (depends on the circumstances and type of actor).
     *
     * @param actor The actor performing the AOE action with their AOE-capable weapon.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" activates the skill of Giant Hammer on " +target + " at " + direction;

    }
}