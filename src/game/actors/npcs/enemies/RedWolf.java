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

import static game.weather.WeatherManager.addAffectedByWeather;
import static game.weather.WeatherManager.removeAffectedByWeather;

/**
 * A class representing a Red Wolf enemy in the game.
 * Red Wolves are hostile enemies that can attack Player actors and drop items upon defeat.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class RedWolf extends Enemy implements AffectedByWeather {
    private int intrinsicDamage = 15;


    /**
     * Constructor for the RedWolf class.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        this.getIntrinsicWeapon();
        this.addBehaviour(100, new FollowBehaviour());
        this.addBalance(25);
        this.setSpawnRate(0.3);
        addAffectedByWeather(this);
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
        removeAffectedByWeather(this);
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
        this.intrinsicDamage = 45;
        this.setSpawnRate(0.3);
        return "The red wolves are becoming less active.\n" +
                "The red wolves are becoming more aggressive.";
    }

    /**
     * Modifies the Red Wolf's behavior and attributes during rainy weather.
     *
     * @return A message describing the Red Wolf's modifications during rainy weather.
     */
    @Override
    public String rainyWeatherModifications() {
        this.intrinsicDamage = 15;
        this.setSpawnRate(0.45);
        return "The red wolves are becoming more active.";
    }


}


