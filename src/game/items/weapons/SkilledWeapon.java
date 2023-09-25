package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actions.AOEAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actions.StabAndStepAction;

/**
 * An abstract class representing a skilled weapon that extends the capabilities of a standard weapon.
 * Skilled weapons can have special skills and temporary effects when used.
 */
public abstract class SkilledWeapon extends WeaponItem {

    private int remainingTurns;
    private boolean skillActivated;
    private int initialHitRate;

    private Actor wielder = null;

    /**
     * Constructor for the SkilledWeapon class.
     *
     * @param name            The name of the weapon.
     * @param displayChar     The character to use for display when the weapon is on the ground.
     * @param damage          The amount of damage this weapon does.
     * @param verb            The verb to use for this weapon, e.g., "hits", "zaps".
     * @param hitRate         The probability/chance to hit the target.
     * @param remainingTurns  The number of remaining turns for a special skill effect.
     * @param skillActivated  A flag indicating whether the special skill is activated.
     */
    public SkilledWeapon(String name, char displayChar, int damage, String verb, int hitRate, int remainingTurns, boolean skillActivated) {
        super(name, displayChar, damage, verb, hitRate);
        this.initialHitRate = hitRate;
        this.remainingTurns = remainingTurns;
        this.skillActivated = skillActivated;
    }

    /**
     * Sets the remaining turns for a special skill effect.
     *
     * @param givenTurn The number of remaining turns.
     */
    public void setRemainingTurns(int givenTurn) {
        this.remainingTurns = givenTurn;
    }

    /**
     * Gets the remaining turns for a special skill effect.
     *
     * @return The number of remaining turns.
     */
    public int getRemainingTurns() {
        return this.remainingTurns;
    }

    /**
     * Sets the status of the special skill activation.
     *
     * @param status True if the skill is activated, false otherwise.
     */
    public void setSkillStatus(boolean status) {
        this.skillActivated = status;
    }

    /**
     * Gets the status of the special skill activation.
     *
     * @return True if the skill is activated, false otherwise.
     */
    public boolean getSkillStatus() {
        return this.skillActivated;
    }

    /**
     * Ends the special skill effect, resetting the hit rate and damage multiplier.
     */
    public void endSkill() {
        this.updateHitRate(this.initialHitRate);
        this.updateDamageMultiplier(1.0f);
        this.setSkillStatus(false);
    }

    /**
     * Gets the PickUpAction associated with this skilled weapon.
     * It also ends any active special skill effect.
     *
     * @param actor The actor attempting to pick up the weapon.
     * @return A PickUpAction for this skilled weapon.
     */
    public PickUpAction getPickUpAction(Actor actor) {
        this.endSkill();
        this.wielder = actor;
        return new PickUpAction(this);
    }

    /**
     * Performs a tick action for the skilled weapon, which handles the duration of its special skill, if active.
     *
     * @param currentLocation The current location of the actor wielding the skilled weapon.
     * @param actor           The actor wielding the skilled weapon.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.getSkillStatus()) {
            int newRemainingTurns = getRemainingTurns() - 1;
            setRemainingTurns(newRemainingTurns);

            if (newRemainingTurns < 0) {
                endSkill();
            }
        }
    }

    /**
     * Generates a list of allowable actions for an actor targeting a location with this skilled weapon.
     *
     * @param target   The actor being targeted.
     * @param location The location being targeted.
     * @return An ActionList containing allowable actions for the actor.
     */
//    @Override
//    public ActionList allowableActions(Actor target, Location location) {
//        System.out.println("a");
//        ActionList actionList = new ActionList();
////        actionList.add(new AttackAction(target, location.toString(), this));
//        Location locationOfWielder = location.map().locationOf(wielder);
//        for (Exit exit: locationOfWielder.getExits()) {
//            System.out.println("bbb");
//            Location destination = exit.getDestination();
//            if (destination.containsAnActor() && !destination.getActor().hasCapability(Status.NON_HOSTILE)) {
//                AttackAction attackAction = new AttackAction(destination.getActor(), exit.getName(), this);
//                actionList.add(attackAction);
//                if (this.hasCapability(Status.STAB_AND_STEP)) {
//                    actionList.add(new StabAndStepAction(this, attackAction));
//                }
//                if (this.hasCapability(Status.AOE_POSSIBLE)) {
//
//                    actionList.add(new AOEAction(this, target,exit.getName()));
//                }
//            }
//        }
//        return actionList;
//    }


    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if(target.hasCapability(Status.FRIENDLY_TO_ENEMY)) {
            actionList.add(new AttackAction(target, location.toString(), this));
        }

        return actionList;
    }
}



