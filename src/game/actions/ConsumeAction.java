package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import game.Ability;
import game.items.consumables.Consumable;
import game.items.consumables.ConsumableItem;

/**
 * A class representing an action where an actor consumes a consumable item.
 * This action allows an actor to consume a consumable item and gain the specified benefits.
 */
public class ConsumeAction extends Action {
    private ConsumableItem consumable;

    /**
     * Constructor for the ConsumeAction class.
     *
     * @param consumable The consumable item that the actor intends to consume.
     */
    public ConsumeAction(ConsumableItem consumable) {
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
        this.consumable.consume(actor);

        if(this.consumable.hasCapability(Ability.INCREASE_BALANCE)){
            return actor + " consumes " + this.consumable + " and " + this.consumable + " increases " + actor + " balance by " + this.consumable.getBuffedPoints();
        }
        return actor + " consumes " + this.consumable + " and " + this.consumable + " restores the " +
                this.consumable.getModifiedAttribute() + " of " + actor + " by " + this.consumable.getBuffedPoints() + " points.";
    }

    /**
     * Returns a description of the ConsumeAction for use in menus or UI.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumable;
    }
}
