package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.weapons.SkilledWeapon;

public class StabAndStepAction extends Action {

    private SkilledWeapon skilledWeapon;

    private AttackAction attackAction;


    public StabAndStepAction(SkilledWeapon skilledWeapon, AttackAction attackAction) {
        this.skilledWeapon = skilledWeapon;
        this.attackAction = attackAction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int staminaNeeded = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.25);

        if (actor.getAttribute(BaseActorAttributes.STAMINA) >= staminaNeeded){
            skilledWeapon.setSkillStatus(true);
            skilledWeapon.setRemainingTurns(1);

            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, staminaNeeded);
            skilledWeapon.addCapability(Status.STAB_AND_STEP);
            String result = attackAction.execute(actor,map);
            Location locationOfWielder = map.locationOf(actor);
            for (Exit exit: locationOfWielder.getExits()) {
                Location destination = exit.getDestination();
                if (!destination.containsAnActor() && destination.canActorEnter(actor)) {
                    MoveActorAction moveActorAction = new MoveActorAction(destination,exit.getName());
                    result += "\n" + moveActorAction.execute(actor,map);
                    break;
                }
            }
            result += "\n" + actor + " takes a deep breath and focuses all their might!";
            return result;
        }

        return actor + " does not have enough stamina to activate the skill of Great Knife!";

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the Stab and Step Skill.";
    }
}
