package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
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
 * A class representing a Forest Keeper enemy actor in the game.
 * Forest Keepers are hostile by default and can be attacked by actors with the HOSTILE_TO_ENEMY capability.
 * They are affected by both sunny and rainy weather, with their behavior and spawn rate changing accordingly.
 */
public class ForestKeeper extends Enemy implements AffectedByWeather {
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
     * Drops a healing vial upon defeat with a random chance.
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

    /**
     * Modifies Forest Keeper behavior during sunny weather.
     *
     * @return A message describing the modifications due to sunny weather.
     */
    public String sunnyWeatherModifications(){
        this.setSpawnRate(this.getSpawnRate() * 2);
        return "The forest keepers are becoming more active";
    }

    /**
     * Modifies Forest Keeper behavior during rainy weather.
     *
     * @return A message describing the modifications due to rainy weather.
     */
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

    /**
     * Overrides the playTurn method to handle weather-related modifications to Forest Keeper behavior.
     *
     * @param actions    A collection of possible actions for the Forest Keeper.
     * @param lastAction The action the Forest Keeper took last turn.
     * @param map        The GameMap containing the Forest Keeper.
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
