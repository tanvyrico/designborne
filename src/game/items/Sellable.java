package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be sold by actors in the game.
 * Classes implementing this interface should define the sell logic and specify the selling price.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public interface Sellable {

    /**
     * Sells the item to an actor and returns a message indicating the result of the sale.
     *
     * @param actor The actor selling the item.
     * @return A message indicating the result of the sale.
     */
    String sell(Actor actor);

    /**
     * Gets the selling price of the item when sold by an actor.
     *
     * @return The selling price of the item.
     */
    int getSellingPrice();
}
