package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * An interface representing a consumable item that can be used by an actor.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public interface Consumable {

    /**
     * Consumes the item and performs its effects on the actor.
     *
     * @param actor The actor consuming the item.
     * @return An integer value representing the effect or change applied to the actor.
     */
    String consume(Actor actor);

}

