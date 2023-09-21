package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FocusAction;
import game.Status;
/**
 * Class representing a Broadsword, a type of weapon that can perform a special "Focus" skill.
 */
public class BroadSword extends SkilledWeapon {

    /**
     * Constructor for the BroadSword class.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "slashes", 80, 5, false);
        this.addCapability(Status.FOCUS_SKILL);
    }



    /**
     * Generates a list of allowable actions for the owner of this BroadSword, which includes a "Focus" action.
     *
     * @param owner The actor who owns this BroadSword.
     * @return An ActionList containing allowable actions for the owner.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new FocusAction(this));
        return actionList;
    }


}
