package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.resettables.Resettable;
import game.actions.TravelAction;
import game.actions.UnlockGateAction;
import game.capabilities.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a gate on the game map.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */

public class Gate extends Ground implements Resettable {
    private boolean isUnlocked;
    private HashMap<Location, String> teleportLocation;




    /**
     * Constructor for the Gate class.
     *
     * @param location   The Location of the gate on the game map.
     * @param destination The destination of the gate, typically another location or area.
     */
    public Gate(Location location, String destination) {
        super('=');
        this.teleportLocation = new HashMap<>();
        this.teleportLocation.put(location, destination);
        this.isUnlocked = false;
    }

    public Gate(HashMap<Location, String> teleportLocation) {
        super('=');
        this.isUnlocked = false;
        this.teleportLocation = teleportLocation ;

    }

    /**
     * Checks whether an actor can enter the gate.
     *
     * @param actor The actor attempting to enter the gate.
     * @return True if the gate is unlocked and the actor has the capability "HOSTILE_TO_ENEMY," allowing them to enter; otherwise, false.
     */
    public boolean canActorEnter(Actor actor) {
        if (this.isUnlocked) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Toggles the locked status of the gate.
     */
    public void toggleLockedStatus() {
        this.isUnlocked = !this.isUnlocked;
    }

    /**
     * Generates a list of allowable actions for an actor at the gate location.
     *
     * @param actor     The actor attempting to perform actions at the gate.
     * @param location  The Location of the gate on the game map.
     * @param direction The direction in which the actor is facing.
     * @return An ActionList containing allowable actions for the actor at the gate.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!this.isUnlocked) {
            actionList.add(new UnlockGateAction(this));
        } else {
            for (Location travelLocation : teleportLocation.keySet()) {
                actionList.add(new TravelAction(travelLocation, teleportLocation.get(travelLocation)));
            }
        }
        return actionList;
    }

    @Override
    public void reset() {
        this.isUnlocked = false;
    }
}
