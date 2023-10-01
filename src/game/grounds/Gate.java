package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.actions.UnlockGateAction;
import game.capabilities.Status;

/**
 * Class representing a gate on the game map.
 */
public class Gate extends Ground {
    private GameMap gameMap;
    private Location location;
    private String destination;
    private boolean isUnlocked;

    /**
     * Constructor for the Gate class.
     *
     * @param gameMap    The GameMap where the gate is located.
     * @param location   The Location of the gate on the game map.
     * @param destination The destination of the gate, typically another location or area.
     */
    public Gate(GameMap gameMap, Location location, String destination) {
        super('=');
        this.gameMap = gameMap;
        this.location = location;
        this.destination = destination;
        this.isUnlocked = false;
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
        if (!(actor.hasCapability(Status.HOSTILE_TO_ENEMY))) {
            actionList.add(new DoNothingAction());
            return actionList;
        }
        if (!this.isUnlocked) {
            actionList.add(new UnlockGateAction(this));
        } else {
            actionList.add(new TravelAction(gameMap.at(location.x(), location.y()), this.destination));
        }
        return actionList;
    }
}
