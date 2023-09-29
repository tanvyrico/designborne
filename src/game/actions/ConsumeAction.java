package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import game.items.consumables.Consumable;

/**
 * A class representing an action where an actor consumes a consumable item.
 * This action allows an actor to consume a consumable item and gain the specified benefits.
 */
public class ConsumeAction extends Action {
    private Consumable consumable;

    /**
     * Constructor for the ConsumeAction class.
     *
     * @param consumable The consumable item that the actor intends to consume.
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the ConsumeAction, allowing the actor to consume the specified consumable item.
     * After consumption, the consumable item is removed from the actor's inventory.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the consumption and its effects.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.consumable.consume(actor);
    }

    /**
     * Returns a description of the ConsumeAction for use in menus.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumable;
    }
}
