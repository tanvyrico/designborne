package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.resettables.Resettable;
import game.capabilities.Ability;
import game.actions.ConsumeAction;

import static game.resettables.ResettableManager.addResettable;
import static game.resettables.ResettableManager.removeResettable;

/**
 * Represents a Runes item in the game, which is consumable and can be used to increase an actor's balance.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public class Runes extends Item implements Consumable, Resettable {
    private int quantity;
    private Location location;
    private boolean isInResettables;

    /**
     * Constructs a Runes item with the specified quantity of runes.
     *
     * @param quantity The quantity of runes in this item.
     */
    public Runes(int quantity) {
        super(quantity + " Runes", '$', true);
        this.quantity = quantity;
        this.addCapability(Ability.INCREASE_BALANCE);
        addResettable(this);
        isInResettables = true;
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
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();

        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpAction(this);
        return null;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.
     */
    @Override
    public void tick(Location currentLocation) {
        if(!isInResettables) {
            addResettable(this);
            isInResettables = true;
        }
        this.location = currentLocation;
    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        if (isInResettables) {
            removeResettable(this);
            isInResettables = false;
        }
    }

    /**
     * remove rune from the map when restarted
     */
    @Override
    public void reset() {
        this.location.removeItem(this);
    }
}

