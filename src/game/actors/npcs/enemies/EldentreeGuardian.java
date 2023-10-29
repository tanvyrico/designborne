package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.FollowBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.capabilities.Ability;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.consumables.Runes;

import java.util.Random;

import static game.weather.WeatherManager.removeAffectedByWeather;

/**
 * A class representing a Elden tree guardian enemy in the game.
 * @author Willson Louisse HV
 * Modified by: Group6
 */

public class EldentreeGuardian extends Enemy{

    private final float DROP_VIAL_CHANCE = 0.25F;

    private final float DROP_FLASK_CHANCE = 0.15f;

    private int intrinsicDamage = 50;
    /**
     * Constructor for the Eldentree Guardian class.
     */
    public EldentreeGuardian(GameMap gameMap) {
        super("Eldentree Guardian", 'e', 250, gameMap);
        this.getIntrinsicWeapon();
        this.addBehaviour(100, new FollowBehaviour());
        this.addBehaviour(999,new WanderBehaviour());
        this.addBalance(250);
        this.setSpawnRate(0.2);
        this.addCapability(Ability.VOID_INVINCIBILITY);
    }
    /**
     * spawn EldentreeGuardian
     *
     * @return A new instance of an enemy.
     */
    @Override
    public Enemy spawnEnemy(GameMap gameMap) {
        return new EldentreeGuardian(gameMap);
    }

    /**
     * Retrieves the intrinsic weapon used by the player character.
     *
     * @return An IntrinsicWeapon representing the player character's damage capability.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "kick", 80);
    }

    /**
     * Handles the outcome when the Forest Keeper becomes unconscious.
     * Drops a healing vial and refreshing flask upon defeat with a random chance.
     *
     * @param actor The actor that defeated the Forest Keeper.
     * @param map   The GameMap where the Forest Keeper was defeated.
     * @return A message describing the outcome of the Forest Keeper's defeat.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        Location location = map.locationOf(this);
        map.removeActor(this);
        if (random.nextDouble() <= DROP_VIAL_CHANCE) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }
        if (random.nextDouble() <= DROP_FLASK_CHANCE) {
            RefreshingFlask refreshingFlask = new RefreshingFlask();
            location.addItem(refreshingFlask);
        }

        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }


}
