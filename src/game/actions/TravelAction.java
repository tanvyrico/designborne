package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A specialized action for an actor to travel to a specified destination on a game map.
 * @author Lim Hung Xuan
 */
public class TravelAction extends MoveActorAction {
    private String destination;

    /**
     * Constructor for the TravelAction class.
     *
     * @param moveToLocation The location to which the actor is traveling.
     * @param destination    A string describing the destination.
     */
    public TravelAction(Location moveToLocation, String destination) {
        super(moveToLocation, destination);
        this.destination = destination;
    }

    /**
     * Returns a description of the TravelAction for use in menus or UI.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.destination;
    }
}
