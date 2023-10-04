package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.weapons.FocusActionCapable;
<<<<<<< HEAD
=======
import game.items.weapons.SkilledWeapon;
>>>>>>> TASK_5_Branch

/**
 * A class representing the 'Focus' action, the special skill of Broadsword.
 *
 */
public class FocusAction extends Action {

    private final float INCREASE_AMOUNT_MULTIPLIER = 0.1f;

    private final int NEW_HIT_RATE = 90;

    private final int ACTIVATION_TURN = 5;

    private FocusActionCapable weapon;

    /**
     * Constructor for the FocusAction class.
     *
     * @param skilledWeapon The skilled weapon for which the actor intends to activate the special ability.
     */
    public FocusAction(FocusActionCapable skilledWeapon){
        this.weapon= skilledWeapon;
    }

    /**
     * Executes the FocusAction, allowing the actor to activate the special ability of a skilled weapon.
     * If the actor has enough stamina, the skilled weapon's special ability is activated, and the actor's stamina is reduced.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the activation of the special ability and its effects.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int staminaNeeded = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.2);
        if (actor.getAttribute(BaseActorAttributes.STAMINA) >= staminaNeeded){
            weapon.setSkillTurn(ACTIVATION_TURN);
            weapon.increaseDamageMultiplierAndHitRate(INCREASE_AMOUNT_MULTIPLIER,NEW_HIT_RATE);
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaNeeded);
            return actor + " takes a deep breath and focuses all their might!";
        }

        else{
            return actor + " does not have enough stamina to activate the skill of Broadsword!";
        }

    }

    /**
     * Returns a description of the FocusAction for use in menus.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Broadsword";
    }

}
