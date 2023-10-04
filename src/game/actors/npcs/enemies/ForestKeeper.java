package game.actors.npcs.enemies;

<<<<<<< HEAD
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
=======
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Status;
>>>>>>> TASK_5_Branch
import game.actors.behaviours.FollowBehaviour;
import game.items.consumables.Runes;
import game.items.consumables.HealingVial;
import game.weather.AffectedByRainyWeather;
import game.weather.AffectedBySunnyWeather;


import java.util.Random;

<<<<<<< HEAD
/**
 * A class representing the Forest Keeper enemy actor in the game.
 * The Forest Keeper is an enemy that can be defeated by other actors.
 * It has specific characteristics, such as a name, display character, hit points, and behaviors.
 * The Forest Keeper also has an intrinsic weapon for attacking, and it may drop items upon defeat.
 * The spawn rate of the Forest Keeper determines how frequently it appears in the game.
 */
public class ForestKeeper extends Enemy {
    private final int intrinsicDamage = 25;
=======
public class ForestKeeper extends Enemy implements AffectedBySunnyWeather, AffectedByRainyWeather {
    private int intrinsicDamage = 25;
>>>>>>> TASK_5_Branch

    private final double spawnRate = 0.15;

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
     * Retrieves the spawn rate of the Forest Keeper.
     *
     * @return The spawn rate of the Forest Keeper.
     */
    @Override
    public double getSpawnRate(){return this.spawnRate;}

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
<<<<<<< HEAD
=======

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

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        if (map.locationOf(this).getGround().hasCapability(Status.SUNNY)){
            display.println(sunnyWeatherModifications());
        }else if (map.locationOf(this).getGround().hasCapability(Status.RAINY)){
            display.println(rainyWeatherModifications());
        }
        return super.playTurn(actions,lastAction,map,display);
}


>>>>>>> TASK_5_Branch
}
