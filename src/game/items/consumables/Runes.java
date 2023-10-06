package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.capabilities.Ability;
import game.actions.ConsumeAction;
import game.actions.PickUpRunesAction;

/**
 * Represents a Runes item in the game, which is consumable and can be used to increase an actor's balance.
 */
public class Runes extends Item implements Consumable {
    private int quantity;

    /**
     * Constructs a Runes item with the specified quantity of runes.
     *
     * @param quantity The quantity of runes in this item.
     */
    public Runes(int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
        this.addCapability(Ability.INCREASE_BALANCE);
    }

    /**
     * Retrieves the quantity of runes in this item.
     *
     * @return The quantity of runes.
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Returns a PickUpAction to allow an actor to pick up this item if it is portable.
     *
     * @param actor The actor attempting to pick up the item.
     * @return A PickUpAction for picking up the Runes item, or null if it's not portable.
     */
    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpRunesAction(this);
        return null;
    }

    /**
     * Consumes the Runes, increasing the actor's balance by the quantity of runes in this item.
     *
     * @param actor The actor consuming the Runes.
     * @return A message describing the consumption and the increased balance of the actor.
     */
    @Override
    public String consume(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " increases " + actor + " balance by " + this.quantity;
    }

    /**
     * Returns a list of allowable actions that an owner of this item can perform.
     * In this case, it includes the ability to consume the Runes item.
     *
     * @param owner The actor who owns the Runes item.
     * @return A list of allowable actions for the owner of the item.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }
}

