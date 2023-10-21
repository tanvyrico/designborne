package game.grounds;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettables;
import game.actions.TravelAction;
import game.actions.UnlockGateAction;
import game.capabilities.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static game.ResettableManager.addResettable;

/**
 * Class representing a gate on the game map.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public class Gate extends Ground implements Resettables {
    private ArrayList<GameMap> gameMaps;
    private ArrayList<Location> locations;
    private ArrayList<String> destinations;
    private boolean isUnlocked;


    public Gate(ArrayList<GameMap> gameMaps, ArrayList<Location> locations, ArrayList<String> destinations) {
        super('=');
        this.gameMaps = gameMaps;
        this.locations = locations;
        this.destinations = destinations;
        this.isUnlocked = false;
        addResettable(this);
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
            for (int index = 0; index < this.locations.size(); index++) {
                actionList.add(new TravelAction(gameMaps.get(index).at(locations.get(index).x(), locations.get(index).y()), destinations.get(index)));
            }
        }
        return actionList;
    }

    @Override
    public void reset() {
        this.isUnlocked = false;
    }
}

