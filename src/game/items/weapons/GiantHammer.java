package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;
import game.actions.GreatSlamAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.items.Sellable;

/**
 * A class representing a Giant Hammer weapon item in the game.
 * The Giant Hammer is a powerful melee weapon that can be used to attack and deal high damage to enemies.
 * It can also be sold by actors who possess the capability to trade.
 */
public class GiantHammer extends WeaponItem implements Sellable {
    private int sellingPrice = 250;

    /**
     * Constructor for the GiantHammer class.
     * Initializes the Giant Hammer with a name, display character, damage points, verb for attack, and hit rate.
     */
    public GiantHammer() {
        super("Giant Hammer",'P', 160, "slams",90);
    }

    /**
     * Sells the Giant Hammer to an actor, adding its selling price to the actor's balance and removing it from their inventory.
     *
     * @param actor The actor selling the Giant Hammer.
     * @return A message indicating that the Giant Hammer has been sold.
     */
    @Override
    public String sell(Actor actor) {
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

    /**
     * Retrieves the selling price of the Giant Hammer.
     *
     * @return The selling price of the Giant Hammer.
     */
    @Override
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Returns a list of allowable actions that the Giant Hammer allows its owner to perform on other actors.
     * These actions may include attacking, performing area-of-effect attacks, and selling the Giant Hammer.
     *
     * @param target   The target actor on which the actions can be performed.
     * @param location The location of the target actor.
     * @return An ActionList containing the allowable actions for the Giant Hammer.
     */
    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))){
            actionList.add(new AttackAction(target, location.toString(), this));
            actionList.add(new GreatSlamAction(this,target,location.toString()));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

}

