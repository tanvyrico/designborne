package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * A behavior representing an actor's ability to attack hostile actors in adjacent locations.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Retrieves an action for the actor to attack a hostile actor in an adjacent location.
     *
     * @param actor The actor whose behavior is being determined.
     * @param map   The GameMap on which the actor is located.
     * @return An AttackAction if a hostile actor is found in an adjacent location, otherwise null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(destination.getActor(), exit.getName());
                }
            }
        }
        return null;
    }
}
