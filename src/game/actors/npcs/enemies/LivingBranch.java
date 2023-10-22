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
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.consumables.Runes;

import java.util.Random;

public class LivingBranch extends Enemy{
    private int intrinsicDamage = 250;

    /**
     * Constructor for the Enemy class.
     */
    public LivingBranch() {
        super("Living Branch", '?', 75);
        this.getIntrinsicWeapon();
        this.addBalance(250);
        this.setSpawnRate(0.9);
        this.addCapability(Ability.VOID_INVINCIBILITY);
    }

    @Override
    public Enemy spawnEnemy() {
        return new LivingBranch();
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
        if (random.nextDouble() <= 0.25) {
            HealingVial healingVial = new HealingVial();
            location.addItem(healingVial);
        }
        if (random.nextDouble() <= 0.15) {
            RefreshingFlask refreshingFlask = new RefreshingFlask();
            location.addItem(refreshingFlask);
        }

        location.addItem(new Runes(this.getBalance()));
        return this + " met their demise at the hands of " + actor;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        for(Exit exit : location.getExits()){
            Location destination = exit.getDestination();

            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                return new AttackAction(destination.getActor(), exit.getName());
            }
        }

        return new DoNothingAction();
    }
}
