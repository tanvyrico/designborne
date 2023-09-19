package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.FollowBehaviour;
import game.items.consumables.Runes;
import game.items.consumables.HealingVial;

import java.util.Random;

public class RedWolf extends Enemy{
    private final int intrinsicDamage = 30;

    /**
     * Constructor for the RedWolf class.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        this.getIntrinsicWeapon();
    }


    /**
     * Spawns a RedWolf.
     *
     * @return A new instance of the RedWolf enemy.
     */
    public RedWolf spawnEnemy() {
        return new RedWolf();
    }

    /**
     * Retrieves the intrinsic weapon used by the Red Wolf.
     *
     * @return An IntrinsicWeapon representing the Red Wolf's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "bites", 80);
    }

    /**
     * Handles the outcome when the Red Wolf becomes unconscious.
     * Drops a healing vial upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Red Wolf.
     * @param map   The GameMap where the Red Wolf was defeated.
     * @return A message describing the outcome of the Red Wolf's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 0.1) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        location.addItem(new Runes(25));

        return this + " met their demise at the hands of " + actor;
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
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (!destination.getActor().hasAttribute(Status.FRIENDLY_TO_ENEMY)) {
                    AttackBehaviour attackBehaviour = new AttackBehaviour();
                    if (attackBehaviour.getAction(this, map) != null) {
                        return attackBehaviour.getAction(this, map);
                    }
                    FollowBehaviour followBehaviour = new FollowBehaviour(destination.getActor());
                    if (followBehaviour.getAction(this, map) != null){
                        return followBehaviour.getAction(this, map);
                    }
                }
            }
        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }
}


