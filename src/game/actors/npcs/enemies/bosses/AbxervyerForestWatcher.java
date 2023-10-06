package game.actors.npcs.enemies.bosses;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npcs.enemies.Enemy;
import game.capabilities.Ability;
import game.actors.behaviours.FollowBehaviour;
import game.grounds.Gate;
import game.items.consumables.Runes;
import game.utility.FancyMessage;
import game.weather.Weather;

import java.util.ArrayList;
import java.util.Arrays;

import static game.weather.WeatherManager.run;
import static game.weather.WeatherManager.setWeather;

/**
 * A boss-level enemy class representing Abxervyer, The Forest Watcher.
 * Abxervyer is a powerful enemy with the ability to manipulate weather in affected maps. It also has invincibility and follows the player.
 * When defeated, Abxervyer drops runes and opens a gate to the Ancient Woods.
 */
public class AbxervyerForestWatcher extends Enemy {

    private int intrinsicDamage = 80;

    private int hitRate = 25;

    private int turnCount = 0;
    private int targetTurn = 3;

    private ArrayList<Weather> weatherList;

    private int weatherIndex = 0;
    private GameMap gameMap;

    public AbxervyerForestWatcher(GameMap gameMap) {
        super("Abxervyer, The Forest Watcher", 'Y', 2000);
        weatherList = new ArrayList<>(Arrays.asList(Weather.RAINY, Weather.SUNNY));
        this.addBalance(5000);
        this.addCapability(Ability.VOID_INVINCIBILITY);
        this.addBehaviour(100, new FollowBehaviour());
        this.gameMap = gameMap;
    }

    /**
     * Retrieves the intrinsic weapon used by Abxervyer.
     *
     * @return An IntrinsicWeapon representing Abxervyer's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "pummels", hitRate);
    }

    /**
     * Handles the outcome when Abxervyer becomes unconscious. Opens a gate to the Ancient Woods and drops runes.
     *
     * @param actor The actor that defeated Abxervyer.
     * @param map   The GameMap where Abxervyer was defeated.
     * @return A message describing the outcome of Abxervyer's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Display display = new Display();
        Location location = map.locationOf(this);
        Gate gate = new Gate(this.gameMap, gameMap.locationOf(this), "The Ancient Woods");
        map.removeActor(this);

        location.setGround(gate);

        location.addItem(new Runes(this.getBalance()));
        display.println(FancyMessage.BOSS_FELLED);
        return this + " met their demise at the hands of " + actor;
    }

    /**
     * Overrides the playTurn method to implement weather manipulation every few turns.
     *
     * @param actions    A collection of possible actions for Abxervyer.
     * @param lastAction The action Abxervyer took last turn.
     * @param map        The GameMap containing Abxervyer.
     * @param display    The Display object for game output.
     * @return The action Abxervyer will perform during this turn.
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turnCount++;
        if (turnCount == targetTurn) {
            turnCount = 0;
            weatherIndex++;
            Weather newWeather = weatherList.get(weatherIndex % weatherList.size());
            display.println(setWeather(newWeather));
        }
        display.println(run());
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Returns null for the spawnEnemy method, as Abxervyer is not intended to respawn.
     *
     * @return Null, as Abxervyer should not respawn.
     */
    @Override
    public Enemy spawnEnemy() {
        return null;
    }
}