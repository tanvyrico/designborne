package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
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
public class Gate extends Ground {
    private GameMap gameMap;
    private Location location;
    private String destination;
    private boolean isUnlocked;
    private String firstDestination;
    private String secondDestination;
    private Location location1;
    private HashMap<String, Location> teleportLocation;
    /**
     * Constructor for the Gate class.
     *
     * @param gameMap    The GameMap where the gate is located.
     * @param location   The Location of the gate on the game map.
     * @param destination The destination of the gate, typically another location or area.
     */
    public Gate(Location location, String destination) {
        super('=');
//        this.gameMap = gameMap;
//        this.location = location;
//        this.destination = destination;
        this.teleportLocation = new HashMap<>();
        this.teleportLocation.put(destination, location);
        this.isUnlocked = false;
    }

    public Gate(HashMap<String, Location> teleportLocation) {
        super('=');
//        this.location = location;
//        this.location1 = location1;
//        this.firstDestination = firstDestination;
//        this.secondDestination = secondDestination;
        this.isUnlocked = false;
        this.teleportLocation = new HashMap<>();
        for (Map.Entry<String, Location> entry : teleportLocation.entrySet()){
            String destination = entry.getKey();
            Location locationOfTeleport = entry.getValue();
            this.teleportLocation.put(destination, locationOfTeleport);
        }
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
//        } else if (location != null & location1 != null){
//            actionList.add(new TravelAction(this.location, this.firstDestination));
//            actionList.add(new TravelAction(this.location1, this.secondDestination));
        } else {
            for (Map.Entry<String, Location> entry : teleportLocation.entrySet()) {
                String destination = entry.getKey();
                Location teleportLocation = entry.getValue();
                actionList.add(new TravelAction(teleportLocation,destination));
            }
//            actionList.add(new TravelAction(this.location, this.destination));
        }
        return actionList;
    }
}
