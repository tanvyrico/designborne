package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
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
import game.weather.AffectedByRainyWeather;
import game.weather.AffectedBySunnyWeather;

import java.util.Random;

public class RedWolf extends Enemy implements AffectedBySunnyWeather, AffectedByRainyWeather {
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

        location.addItem(new Runes(this.getBalance()));

        return this + " met their demise at the hands of " + actor;
    }

    public String sunnyWeatherModifications(){
        this.intrinsicDamage = this.intrinsicDamage * 3;
        this.setSpawnRate(this.getSpawnRate() / 1.5);
        return "The red wolves are becoming more active. \n The red wolves are becoming more aggressive.";
    }

    @Override
    public String rainyWeatherModifications() {
        this.setSpawnRate(this.getSpawnRate() * 1.5);
        return "The red wolves are becoming more active.";
    }

}


