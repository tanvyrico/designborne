package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Status;
import game.actors.behaviours.FollowBehaviour;
import game.items.consumables.Runes;
import game.items.consumables.HealingVial;
import game.weather.AffectedByWeather;

import java.util.Random;

/**
 * A class representing a Red Wolf enemy in the game.
 * Red Wolves are hostile enemies that can attack Player actors and drop items upon defeat.
 */
public class RedWolf extends Enemy implements AffectedByWeather {
    private int intrinsicDamage = 30;


    /**
     * Constructor for the RedWolf class.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        this.getIntrinsicWeapon();
        this.addBehaviour(100, new FollowBehaviour());
        this.addBalance(25);
        this.setSpawnRate(0.3);
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
        return new IntrinsicWeapon((int)(intrinsicDamage * getIntrinsicDamageMultiplier()), "bites", 80);
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

        location.addItem(new Runes(this.getBalance()));

        return this + " met their demise at the hands of " + actor;
    }

    /**
     * Modifies the Red Wolf's behavior and attributes during sunny weather.
     *
     * @return A message describing the Red Wolf's modifications during sunny weather.
     */
    public String sunnyWeatherModifications(){
        this.setIntrinsicDamageMultiplier(3F);
        this.setSpawnRateMultiplier(1f);
        this.intrinsicDamage = this.intrinsicDamage * 3;
        return "The red wolves are becoming more active. \n The red wolves are becoming more aggressive.";
    }

    /**
     * Modifies the Red Wolf's behavior and attributes during rainy weather.
     *
     * @return A message describing the Red Wolf's modifications during rainy weather.
     */
    @Override
    public String rainyWeatherModifications() {
        this.setIntrinsicDamageMultiplier(1f);
        this.setSpawnRateMultiplier(1.5f);
        return "The red wolves are becoming more active.";
    }

    /**
     * Determines the action to be performed by the Red Wolf during its turn.
     *
     * @param actions    A collection of possible actions for the Red Wolf.
     * @param lastAction The action the Red Wolf took last turn.
     * @param map        The GameMap containing the Red Wolf.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action to be performed during this turn.
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){

        if (map.locationOf(this).getGround().hasCapability(Status.SUNNY)){
            display.println(sunnyWeatherModifications());

        }else if (map.locationOf(this).getGround().hasCapability(Status.RAINY)){
            display.println(rainyWeatherModifications());
        }
        return super.playTurn(actions,lastAction,map,display);
    }

}


