package game.actors.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.Status;

/**
 * A behavior class representing the behavior of an actor following a target actor.
 * This behavior is typically used for hostile actors to pursue a target with a specific capability.
 */
public class FollowBehaviour implements Behaviour {

    private Actor target ;

    /**
     * Constructs a FollowBehaviour instance with an initial target set to null.
     */
    public FollowBehaviour() {
        this.target = null;
    }

    /**
     * Retrieves the action for an actor to follow the target actor based on their current position.
     *
     * @param actor The actor performing the behavior.
     * @param map   The GameMap where the behavior occurs.
     * @return An action for the actor to follow the target or null if no valid action can be taken.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        if (target == null){
            for (Exit exit : here.getExits() ){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()){
                    if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                        target = destination.getActor();
                    }
                }
            }
        }

        if (target != null){
            if(!map.contains(target) || !map.contains(actor))
                return  null;
            Location there = map.locationOf(target);
            int currentDistance = distance(here, there);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }

        return null;
    }

    /**
     * Computes the Manhattan distance between two locations.
     *
     * @param a The first location.
     * @param b The second location.
     * @return The number of steps between location a and location b if movement is allowed in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
