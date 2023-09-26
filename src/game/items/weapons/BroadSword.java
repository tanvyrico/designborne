package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Ability;
import game.actions.*;
import game.Status;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a Broadsword, a type of weapon that can perform a special "Focus" skill.
 */
public class BroadSword extends WeaponItem implements Purchasable, Sellable, FocusActionCapable {
    private int sellingPrice = 100;
    private int purchasePrice = 250;

    private int specialSkillTurn = 0;

    private int initialHitRate;

    /**
     * Constructor for the BroadSword class.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "slashes", 80);
        this.initialHitRate = 80;
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
    }

    public void setSkillTurn(int turn){
        this.specialSkillTurn = turn;
    }

    public void increaseDamageMultiplierAndHitRate(float damageMultiplier,int hitRate){
        this.increaseDamageMultiplier(damageMultiplier);
        this.updateHitRate(hitRate);
    }

    public void decrementSkillTurn(){
        this.specialSkillTurn -= 1;
    }

    public void resetWeaponStat(){
        this.updateHitRate(initialHitRate);
        this.updateDamageMultiplier(1f);
    }


    public void tick(Location currentLocation, Actor actor) {
        decrementSkillTurn();
        if ( this.specialSkillTurn == 0){
            resetWeaponStat();
        }
    }

    /**
     * Generates a list of allowable actions for the owner of this BroadSword, which includes a "Focus" action.
     *
     * @param owner The actor who owns this BroadSword.
     * @return An ActionList containing allowable actions for the owner.
     */

    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new FocusAction(this));
        return actionList;
    }

    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if(!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))) {
            actionList.add(new AttackAction(target, location.toString(), this));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }

        return actionList;
    }

    public String purchase(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.05) {
            return "purchase failed!";
        }
        if (actor.getBalance() >= this.purchasePrice){
            actor.deductBalance(this.purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }
        return "purchase failed!";
    }

    @Override
    public String sell(Actor actor) {
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

}

