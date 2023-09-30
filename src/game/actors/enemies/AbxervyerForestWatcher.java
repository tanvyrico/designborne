package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Ability;
import game.Status;
import game.actors.behaviours.FollowBehaviour;
import game.grounds.Gate;
import game.grounds.maps.WeatherMaps;
import game.items.consumables.Runes;
import game.utility.FancyMessage;
import java.util.ArrayList;
import java.util.Arrays;


public class AbxervyerForestWatcher extends Enemy {

    private int intrinsicDamage = 80;

    private int hitRate = 25;

    private int turnCount = 0;

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
        Display display = new Display();
        Location location = map.locationOf(this);
        Gate gate = new Gate(map, location, "The Ancient Woods");
        map.removeActor(this);

        location.setGround(gate);

        location.addItem(new Runes(this.getBalance()));
        display.println(FancyMessage.BOSS_FELLED);
        return this + " met their demise at the hands of " + actor;
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnCount += 1;
        if (turnCount % 3 == 0) {
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