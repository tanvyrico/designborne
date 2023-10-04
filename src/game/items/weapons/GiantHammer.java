package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;
import game.actions.AOEAction;
import game.actions.AttackAction;
<<<<<<< HEAD
import game.items.Sellable;

public class GiantHammer extends WeaponItem implements Sellable {
    /**
     * Constructor for the SkilledWeapon class.
     */

    private int sellingPrice = 250;
=======
import game.actions.SellAction;
import game.items.Sellable;

public class GiantHammer extends WeaponItem implements Sellable {
    private int sellingPrice = 250;
    /**
     * Constructor for the SkilledWeapon class.
     */
>>>>>>> TASK_5_Branch
    public GiantHammer() {
        super("Giant Hammer",'P', 160, "slams",90);
    }

<<<<<<< HEAD
    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))){
            actionList.add(new AttackAction(target, location.toString(), this));
            actionList.add(new AOEAction(this,target,location.toString()));
        }
        return actionList;
    }

=======
>>>>>>> TASK_5_Branch
    @Override
    public String sell(Actor actor) {
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

    @Override
    public int getSellingPrice() {
        return sellingPrice;
    }

<<<<<<< HEAD
}
=======
    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))){
            actionList.add(new AttackAction(target, location.toString(), this));
            actionList.add(new AOEAction(this,target,location.toString()));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }

        return actionList;
    }

}

>>>>>>> TASK_5_Branch
