package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;


/**
 * Class representing the Void, a bottomless pit, on the game map that kills actors when stepped on.
 */
public class Void extends Ground {

    /**
     * Constructor for the Void class.
     */
    public Void() {
        super('+');
    }

    /**
     * Checks if an actor is present at the specified location on the map. If an actor is present,
     * it makes the actor unconscious by removing them from the game map.
     *
     * @param location The Location on the game map representing the void ground.
     */
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (location.containsAnActor()) {
            actor.unconscious(location.map());
        }
    }
}
