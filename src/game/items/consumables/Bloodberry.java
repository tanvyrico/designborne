package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.items.Sellable;

/**
 * Represents a Bloodberry item in the game, which is both consumable and sellable.
 * Consuming a Bloodberry restores an actor's health attribute by a fixed amount.
 * It can also be sold to a trader for a specific selling price.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class Bloodberry extends Item implements Consumable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private final int sellingPrice = 10;

    /**
     * Constructs a Bloodberry item with the name "Bloodberry" and a display character '*'.
     */
    public Bloodberry() {
        super("Bloodberry", '*', true);
    }

    /**
     * Consumes the Bloodberry item, increasing an actor's maximum health attribute.
     *
     * @param actor The actor consuming the Bloodberry.
     * @return A message describing the consumption and its effects.
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 5);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + 5 + " points.";
    }

    /**
     * Returns a list of allowable actions that an owner of this item can perform.
     * In this case, it includes the ability to consume the Bloodberry.
     *
     * @param owner The actor who owns the Bloodberry.
     * @return A list of allowable actions for the owner of the item.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    /**
     * Sells the Bloodberry item to a trader actor, adding its selling price to the trader's balance.
     *
     * @param actor The actor selling the Bloodberry to the trader.
     * @return A message describing the sale and the updated balance of the actor.
     */
    @Override
    public String sell(Actor actor) {
        actor.addBalance(this.sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this + " at its normal price (" + this.sellingPrice +" runes)";
    }

    /**
     * Retrieves the selling price of the Bloodberry item.
     *
     * @return The selling price of the Bloodberry item.
     */
    @Override
    public int getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * Returns a list of allowable actions that a trader actor can perform with this item.
     * In this case, it includes the ability to sell the Bloodberry to the trader.
     *
     * @param target The trader actor interacting with the Bloodberry.
     * @param location The location where the interaction takes place.
     * @return A list of allowable actions for the trader actor.
     */
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

}

