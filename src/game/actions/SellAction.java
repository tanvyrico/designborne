package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

/**
 * A class representing the action of selling a sellable item by an actor.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class SellAction extends Action {
    private Sellable sellable;

    /**
     * Constructor for the SellAction class.
     *
     * @param sellable The sellable item to be sold.
     */
    public SellAction(Sellable sellable){
        this.sellable = sellable;
    }

    /**
     * Executes the SellAction, allowing the actor to sell a sellable item.
     * The actor's balance is updated, and the sale is performed through the sellable object.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the successful sale and the updated balance.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.sellable.sell(actor);
    }

    /**
     * Returns a description of the SellAction for use in menus.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.sellable + " for " + this.sellable.getSellingPrice() + " runes";
    }
}
