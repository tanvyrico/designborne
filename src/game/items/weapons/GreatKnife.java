package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AOEAction;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.StabAndStepAction;


public class GreatKnife extends SkilledWeapon{


    /**
     * Constructor for the SkilledWeapon class.
     */
    public GreatKnife() {
        super("GreatKnife", '>', 75, "Stab", 70, 5, false);
        addCapability(Status.STAB_AND_STEP);
    }

    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.FRIENDLY_TO_ENEMY)){
            actionList.add(new StabAndStepAction(this, new AttackAction(target, location.toString(), this)));
        }

        return actionList;
    }
}
