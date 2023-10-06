package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Ability;


/**
 * Class representing the Void, a bottomless pit, on the game map that kills actors when stepped on.
 * @author Lim Hung Xuan
 * Modified by: Group6
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
//    public void tick(Location location) {
//        Actor actor = location.getActor();
//        if (!actor.hasCapability(Ability.VOID_INVINCIBILITY)) {
//            if (location.containsAnActor()) {
//                actor.unconscious(location.map());
//            }
//        }
//    }

    public void tick(Location location) {
        Actor actor = location.getActor();
        if (location.containsAnActor() && ! actor.hasCapability(Ability.VOID_INVINCIBILITY)) {
            actor.unconscious(location.map());
        }
    }

}
