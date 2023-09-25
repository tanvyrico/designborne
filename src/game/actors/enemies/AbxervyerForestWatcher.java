package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Ability;
import game.actors.behaviours.FollowBehaviour;
import game.grounds.Gate;
import game.items.consumables.Runes;


public class AbxervyerForestWatcher extends Enemy {

    private int intrinsicDamage = 80;

    private int hitRate = 25;

    /**
     * Constructor for the Enemy class.

     */
    public AbxervyerForestWatcher() {
        super("Abxervyer, The Forest Watcher", 'Y', 2000);
        this.addCapability(Ability.VOID_INVINCIBILITY);
        this.addBehaviour(100, new FollowBehaviour());
        this.addCapability(Ability.CHANGE_WEATHER);
    }

    /**
     * Spawns a ForestKeeper.
     *
     * @return A new instance of the ForestKeeper enemy.
     */
    @Override
    public Enemy spawnEnemy() {
        return new AbxervyerForestWatcher();
    }


    /**
     * Retrieves the intrinsic weapon used by the Forest Keeper.
     *
     * @return An IntrinsicWeapon representing the Forest Keeper's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "WHACK", hitRate);
    }

    /**
     * Handles the outcome when the Forest Keeper becomes unconscious.
     * Drops healing vial upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Forest Keeper.
     * @param map   The GameMap where the Forest Keeper was defeated.
     * @return A message describing the outcome of the Forest Keeper's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        Gate gate = new Gate(map, location, "Congrats You've Defeated The Abxervyer");
        map.removeActor(this);

        location.setGround(gate);

        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        int currentPriority = Integer.MAX_VALUE;
        Action currentAction = null ;
        for (Integer key : getBehaviours().keySet()) {
            Action action = getBehaviours().get(key).getAction(this,map);
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
}
