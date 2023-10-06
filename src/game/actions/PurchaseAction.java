package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

/**
 * An Action representing the purchase of a purchasable item by an actor from a seller.
 * This action allows an actor to purchase a purchasable item, updating their balance accordingly.
 */
public class PurchaseAction extends Action {
    private Purchasable purchasable;

    private Actor seller;

    /**
     * Constructor for the PurchaseAction class.
     *
     * @param purchasable The purchasable item to be purchased.
     * @param seller The actor who is selling the item.
     */
    public PurchaseAction(Purchasable purchasable, Actor seller){
        this.seller = seller;
        this.purchasable = purchasable;
    }

    /**
     * Executes the PurchaseAction, allowing the actor to purchase the specified item from the seller.
     * The actor's balance is updated, and the purchase is performed through the purchasable object.
     *
     * @param actor The actor performing the action (buyer).
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the successful purchase and the updated balance.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.purchasable.purchase(actor,seller);
    }

    /**
     * Returns a description of the PurchaseAction for use in menus.
     *
     * @param actor The actor for whom the description is generated (buyer).
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + this.purchasable + " for " + this.purchasable.getPurchasePrice(seller) + " runes";
    }
}
