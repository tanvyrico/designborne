package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be purchased by actors in the game.
 * Classes implementing this interface should define the purchase logic and price calculation.
 */
public interface Purchasable {

    /**
     * Purchases the item from a seller actor by a buyer actor.
     *
     * @param actor  The actor buying the item.
     * @param seller The actor selling the item.
     * @return A message indicating the result of the purchase.
     */
    String purchase(Actor actor, Actor seller);

    /**
     * Calculates and returns the purchase price of the item when sold by a specific seller actor.
     *
     * @param seller The actor selling the item.
     * @return The purchase price of the item.
     */
    int getPurchasePrice(Actor seller);
}
