package game.actors.npcs.enemies.bosses;

<<<<<<< HEAD
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.capabilities.Ability;
import game.actors.npcs.enemies.Enemy;
import game.grounds.Gate;
import game.items.consumables.Runes;

/**
 * A class representing Abxervyer the Forest Watcher, a powerful enemy in the game.
 * This enemy has high health, an intrinsic weapon, and the ability to drop runes upon defeat.
 */
=======
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npcs.enemies.Enemy;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actors.behaviours.FollowBehaviour;
import game.grounds.Gate;
import game.grounds.maps.WeatherMaps;
import game.items.consumables.Runes;
import game.utility.FancyMessage;
import java.util.ArrayList;
import java.util.Arrays;


>>>>>>> TASK_5_Branch
public class AbxervyerForestWatcher extends Enemy {

    private int intrinsicDamage = 80;

    private int hitRate = 25;

<<<<<<< HEAD
    /**
     * Constructor for the Enemy class.
     */
    public AbxervyerForestWatcher() {
        super("Abxervyer the Forest Watcher", 'Y', 2000);
        this.addCapability(Ability.VOID_INVINCIBILITY);
    }

    /**
     * Gets the spawn rate for this enemy.
     *
     * @return The spawn rate of this enemy, which is always 0.
     */
    @Override
    public double getSpawnRate(){
        return 0.0;
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
=======
    private int turnCount = 0;
    private int targetTurn = 3;

    private ArrayList<WeatherMaps> affectedWeatherMap;

    private ArrayList<Enum<Status>> weatherList;

    private int weatherIndex = 0;


    /**
     * Constructor for the Enemy class.

     */
    public AbxervyerForestWatcher(ArrayList<WeatherMaps> affectedMap ) {
        super("Abxervyer, The Forest Watcher", 'Y', 2000);
        affectedWeatherMap = affectedMap;
        weatherList = new ArrayList<>(Arrays.asList(Status.RAINY, Status.SUNNY));
        this.addBalance(5000);
        this.addCapability(Ability.VOID_INVINCIBILITY);
        this.addBehaviour(100, new FollowBehaviour());
        this.addCapability(Ability.CHANGE_WEATHER);
    }

    /**
>>>>>>> TASK_5_Branch
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
<<<<<<< HEAD
        Location location = map.locationOf(this);
        Gate gate = new Gate(map, location, "Congrats You've Defeated The Abxervyer");
=======
        Display display = new Display();
        Location location = map.locationOf(this);
        Gate gate = new Gate(map, location, "The Ancient Woods");
>>>>>>> TASK_5_Branch
        map.removeActor(this);

        location.setGround(gate);

        location.addItem(new Runes(this.getBalance()));
<<<<<<< HEAD
        return this + " met their demise at the hands of " + actor;
    }


}
=======
        display.println(FancyMessage.BOSS_FELLED);
        return this + " met their demise at the hands of " + actor;
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnCount++;
        if (turnCount == targetTurn) {
            turnCount = 0;
            for (WeatherMaps weatherMap : affectedWeatherMap) {
                Enum<Status> weather = weatherList.get(weatherIndex % weatherList.size());
                weatherMap.setWeather(weather);
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public Enemy spawnEnemy() {
        return null;
    }
}
>>>>>>> TASK_5_Branch
