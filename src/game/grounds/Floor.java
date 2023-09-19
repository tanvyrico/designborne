package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Lim Hung Xuan
 */
public class Floor extends Ground {

    /**
     * Constructor for the Floor class.
     */
    public Floor() {
        super('_');
    }

    /**
     * Checks whether an actor can enter the floor.
     *
     * @param actor The actor attempting to enter the floor.
     * @return True if the actor has the capability "HOSTILE_TO_ENEMY," allowing them to enter; otherwise, false.
     */
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            return true;
        }
        else{
            return false;
        }
    }
}
