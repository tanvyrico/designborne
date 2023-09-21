package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FocusAction;
import game.Status;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a Broadsword, a type of weapon that can perform a special "Focus" skill.
 */
public class BroadSword extends SkilledWeapon {
    private int purchasePrice = 250;
    private int sellingPrice = 100;

    /**
     * Constructor for the BroadSword class.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "slashes", 80, 5, false);
        this.addCapability(Status.FOCUS_SKILL);
    }



    /**
     * Generates a list of allowable actions for the owner of this BroadSword, which includes a "Focus" action.
     *
     * @param owner The actor who owns this BroadSword.
     * @return An ActionList containing allowable actions for the owner.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new FocusAction(this));
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
