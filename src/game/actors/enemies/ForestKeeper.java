package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.FollowBehaviour;
import game.items.consumables.Runes;
import game.items.consumables.HealingVial;
import game.weather.AffectedByRainyWeather;
import game.weather.AffectedBySunnyWeather;


import java.util.Random;

public class ForestKeeper extends Enemy implements AffectedBySunnyWeather, AffectedByRainyWeather {
    private int intrinsicDamage = 25;


    /**
     * Constructor for the ForestKeeper class.
     */
    public ForestKeeper() {
        super("Forest Keeper", '8', 125);
        this.getIntrinsicWeapon();
        this.addBehaviour(100, new FollowBehaviour());
        this.addBalance(50);
        this.setSpawnRate(0.15);
    }

    /**
     * Spawns a ForestKeeper.
     *
     * @return A new instance of the ForestKeeper enemy.
     */
    @Override
    public Enemy spawnEnemy() {
        return new ForestKeeper();
    }

    /**
     * Retrieves the intrinsic weapon used by the Forest Keeper.
     *
     * @return An IntrinsicWeapon representing the Forest Keeper's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "chops", 75);
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
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);

        if (random.nextDouble() <= 0.2) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }

        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }

    public String sunnyWeatherModifications(){
        this.setSpawnRate(this.getSpawnRate() * 2);
        return "The forest keepers are becoming more active";
    }

    @Override
    public String rainyWeatherModifications() {
        String healedMessage = null;
        if(getAttribute(BaseActorAttributes.HEALTH) < this.getAttributeMaximum(BaseActorAttributes.HEALTH)){
            this.heal(10);
            healedMessage = this + " feels rejuvenated. \n";
        }
        this.setSpawnRate(this.getSpawnRate() / 2);
        return healedMessage + "The forest keepers are becoming less active.";
    }

}
