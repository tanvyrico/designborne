package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a refreshing flask item that can be consumed by an actor to restore stamina.
 */
public class RefreshingFlask extends Item implements Consumable, Purchasable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.STAMINA;
    private final int sellingPrice = 25;

    /**
     * Constructor for the RefreshingFlask class.
     */
    public RefreshingFlask() {
        super("Refreshing flask", 'u', true);
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
    }

    /**
     * Consumes the RefreshingFlask and increases the actor's stamina attribute by a percentage of its maximum value.
     *
     * @param actor The actor consuming the flask.
     * @return A message describing the consumption and its effects.
     */
    @Override
    public String consume(Actor actor) {
        int buffedPoints = (int) (0.2 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA));
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, buffedPoints);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + buffedPoints + " points.";
    }

    /**
     * Generates a list of allowable actions for an owner of this item. In this case, it includes a ConsumeAction.
     *
     * @param owner The actor who owns the item.
     * @return An ActionList containing allowable actions for the owner.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    /**
     * Handles the purchase of the RefreshingFlask by an actor from a seller.
     *
     * @param actor  The actor purchasing the item.
     * @param seller The actor selling the item.
     * @return A message describing the purchase transaction.
     */
    public String purchase(Actor actor, Actor seller) {
        int purchasePrice = getPurchasePrice(seller);
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            purchasePrice = (int) (purchasePrice * 0.8);
        }
        if (actor.getBalance() >= purchasePrice){
            actor.deductBalance(purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }else{
            return actor + " failed to purchase " + this + " due to insufficient runes!";
        }

    }
    /**
     * Gets the purchase price of the RefreshingFlask based on the seller's capabilities.
     *
     * @param seller The actor selling the item.
     * @return The purchase price of the flask.
     */
    @Override
    public int getPurchasePrice(Actor seller) {
        if (seller.hasCapability(Status.SUSPICIOUS)){
            return 75;
        }
        return 0;
    }

    /**
     * Handles the sale of the RefreshingFlask by an actor.
     *
     * @param actor The actor selling the item.
     * @return A message describing the sale transaction.
     */
    public String sell(Actor actor){
        Random random = new Random();
        if (random.nextDouble() <= 0.5) {
            actor.removeItemFromInventory(this);
            return actor + " sold " + this + " without being paid!";
        }else {
            actor.addBalance(this.sellingPrice);
            actor.removeItemFromInventory(this);
            return actor + " sold " + this + " at its normal price (" + this.sellingPrice +" runes)";
        }

    }

    /**
     * Gets the selling price of the RefreshingFlask.
     *
     * @return The selling price of the flask.
     */
    public int getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * Generates a list of allowable actions for a target actor and location. In this case,
     * it includes a SellAction if the target actor has the TRADER capability.
     *
     * @param target   The target actor.
     * @param location The location where the actions are considered.
     * @return An ActionList containing allowable actions for the target actor and location.
     */
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }

}


