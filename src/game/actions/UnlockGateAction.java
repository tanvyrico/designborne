package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.grounds.Gate;

/**
 * An action representing an actor unlocking a gate.
 */
public class UnlockGateAction extends Action {
    private Gate gate;

    /**
     * Constructor for the UnlockGateAction class.
     *
     * @param lockedGate The gate that the actor intends to unlock.
     */
    public UnlockGateAction(Gate lockedGate) {
        this.gate = lockedGate;
    }

    /**
     * Executes the UnlockGateAction, allowing the actor to unlock the specified gate if they have the OPEN_GATE capability.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap on which the action is performed.
     * @return A message indicating the result of the unlocking attempt.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.OPEN_GATE)) {
            this.gate.toggleLockedStatus();
            return "Gate is now unlocked.";
        } else {
            return "Gate is locked shut.";
        }
    }

    /**
     * Returns a description of the UnlockGateAction for use in menus or UI.
     *
     * @param actor The actor for whom the description is generated.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }
}
