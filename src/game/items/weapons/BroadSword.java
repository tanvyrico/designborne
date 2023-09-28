package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.Status;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a Broadsword, a type of weapon that can perform a special "Focus" skill.
 */
public class BroadSword extends SkilledWeapon implements Purchasable, Sellable {
    private int sellingPrice = 100;
    private int purchasePrice = 250;

    /**
     * Constructor for the BroadSword class.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "slashes", 80, 5, false);
        this.addCapability(Status.FOCUS_SKILL);
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
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
        if(target.hasCapability(Status.FRIENDLY_TO_ENEMY)) {
            actionList.add(new AttackAction(target, location.toString(), this));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }

        return actionList;
    }

    @Override
    public String purchase(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.05) {
            actor.deductBalance(this.purchasePrice);
            return actor + " purchased but failed to get " + this;
        }else{
            if (actor.getBalance() >= this.purchasePrice){
                actor.deductBalance(this.purchasePrice);
                actor.addItemToInventory(this);
                return actor + " purchased " + this + " at original price (" + this.sellingPrice +" runes)";
            }else {
                return "purchase failed!";
            }
        }
    }


    @Override
    public String sell(Actor actor) {
        actor.addBalance(this.sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this + " at original price (" + this.sellingPrice +" runes)";
    }


}
