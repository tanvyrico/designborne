package game.actors.npcs.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.items.consumables.Bloodberry;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.consumables.Runes;

import java.util.Random;

public class LivingBranch extends Enemy {
    private int intrinsicDamage = 250;

    private float DROP_BERRY_CHANCE = 0.5f;

    /**
     * Constructor for the Enemy class.
     */
    public LivingBranch(GameMap gameMap) {
        super("Living Branch", '?', 75, gameMap);
        this.getIntrinsicWeapon();
        this.addBalance(500);
        this.setSpawnRate(0.9);
        this.addCapability(Ability.VOID_INVINCIBILITY);
    }

    @Override
    public Enemy spawnEnemy(GameMap gameMap) {
        return new LivingBranch(gameMap);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "stab", 90);
    }

    /**
     * Handles the outcome when the Forest Keeper becomes unconscious.
     * Drops a healing vial upon defeat with a random chance.
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
        if (random.nextDouble() <= DROP_BERRY_CHANCE) {
            location.addItem(new Bloodberry());
        }
        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }

}

