
package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resettables.Resettable;
import game.actions.AttackAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.capabilities.Status;


import java.util.HashMap;
import java.util.Map;

import static game.resettables.ResettableManager.addResettable;
import static game.resettables.ResettableManager.removeResettable;

/**
 * An abstract class representing an enemy actor in the game.
 * Enemy actors are hostile by default and can be attacked by actors with the HOSTILE_TO_ENEMY capability, but are friendly to other enemies.
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public abstract class Enemy extends Actor implements Resettable {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private double spawnRate;
    private GameMap currentMap;


    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character used to display the enemy on the game map.
     * @param hitPoints   The initial hit points of the enemy.
     */
    public Enemy(String name, char displayChar, int hitPoints, GameMap gameMap) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.FRIENDLY_TO_ENEMY);
        this.behaviours.put(1, new AttackBehaviour());
        addResettable(this);
        this.currentMap = gameMap;
    }

    /**
     * Determines the action to be performed by the enemy during its turn.
     *
     * @param actions    A collection of possible actions for the enemy.
     * @param lastAction The action the enemy took last turn.
     * @param map        The GameMap containing the enemy.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action to be performed during this turn.
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        int currentPriority = Integer.MAX_VALUE;
        Action currentAction = null ;
        for (Integer key : getBehaviours().keySet()) {
            Action action = getBehaviours().get(key).getAction(this, map);
            if (key < currentPriority & action != null){
                currentPriority = key;
                currentAction = action;
            }
        }

        if (currentAction != null){
            return currentAction;
        }
        return new DoNothingAction();
    }

    /**
     * Adds a behavior with a specified priority to the enemy's behavior list.
     *
     * @param priority  The priority of the behavior.
     * @param behaviour The behavior to be added.
     */
    public void addBehaviour(Integer priority,Behaviour behaviour){
        this.behaviours.put(priority,behaviour);
    }

    /**
     * Overrides the allowableActions method to return attack actions if the enemy is hostile to the actor.
     *
     * @param otherActor The actor to check for hostility.
     * @param direction  The direction of the actor relative to this enemy.
     * @param map        The GameMap containing this enemy.
     * @return A list of allowable actions for this enemy based on its hostility towards other actors.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Returns the behaviors associated with this enemy.
     *
     * @return A map of behaviors, where the key is the behavior's priority and the value is the behavior itself.
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    /**
     * Abstract method to spawn a new enemy.
     *
     * @return A new instance of an enemy.
     */
    public abstract Enemy spawnEnemy(GameMap gameMap);

    /**
     * Gets the spawn rate of this enemy.
     *
     * @return The spawn rate of this enemy.
     */
    public double getSpawnRate(){
        return this.spawnRate;
    }

    /**
     * Sets the spawn rate of this enemy.
     *
     * @param spawnRate The spawn rate to be set for this enemy.
     */
    public void setSpawnRate(double spawnRate){
        this.spawnRate = spawnRate;
    }

    public void reset() {
        if (this.hasCapability(Status.BOSS)) {
            this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, this.getAttributeMaximum(BaseActorAttributes.HEALTH) - this.getAttribute(BaseActorAttributes.HEALTH));
        }
        else{
            this.hurt(this.getAttribute(BaseActorAttributes.HEALTH));
            this.unconscious(currentMap);
            removeResettable(this);
        }
    }
}
