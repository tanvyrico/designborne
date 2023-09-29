package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actions.AOEAction;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.StabAndStepAction;


public class GreatKnife extends WeaponItem {


    /**
     * Constructor for the SkilledWeapon class.
     */
    public GreatKnife() {
        super("GreatKnife", '>', 75, "Stab", 70);
        addCapability(Status.STAB_AND_STEP);
    }

    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))){
            actionList.add(new AttackAction(target, location.toString(), this));
            actionList.add(new StabAndStepAction(this,target,location.toString()));
        }
        return actionList;
    }
}
