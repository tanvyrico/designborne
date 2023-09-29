package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Runes;

/**
 * A class representing the action of picking up runes by an actor.
 */
public class PickUpRunesAction extends PickUpAction {
    private final Runes runes;

    /**
     * Constructor for the PickUpRunesAction class.
     *
     * @param runes The runes to be picked up.
     */
    public PickUpRunesAction(Runes runes) {
        super(runes);
        this.runes = runes;
    }

    /**
     * Executes the PickUpRunesAction, allowing the actor to pick up runes from a location on the map.
     * The runes are removed from the location, and the actor's balance is updated accordingly.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap on which the action is performed.
     * @return A message describing the successful pickup of runes and the updated balance.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(runes);
        actor.addBalance(runes.getQuantity());
        return menuDescription(actor);
    }

    /**
     * Returns a description of the PickUpRunesAction for use in menus.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + runes.getQuantity() + runes;
    }

}
