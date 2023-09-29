package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a refreshing flask item that can be consumed by an actor to restore stamina.
 */
public class RefreshingFlask extends Item implements Consumable, Purchasable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.STAMINA;
    private int sellingPrice = 25;
    private int purchasePrice = 75;

    /**
     * Constructor for the RefreshingFlask class.
     */
    public RefreshingFlask() {
        super("Refreshing flask", 'u', true);
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
    }

    @Override
    public String consume(Actor actor) {
        int buffedPoints = (int) (0.2 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA));
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, buffedPoints);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + buffedPoints + " points.";
    }

    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    public String purchase(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            int luckyPrice = (int) (this.purchasePrice * 0.8);
            actor.deductBalance(luckyPrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this + " with a 20% discount (" + luckyPrice + " runes)";
        }
        if (actor.getBalance() >= this.purchasePrice){
            actor.deductBalance(this.purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this + " at its normal price (" + this.purchasePrice +" runes)";
        }
        return actor + " failed to purchase " + this + " due to insufficient runes!";
    }

    @Override
    public int getPurchasePrice() {
        return this.purchasePrice;
    }


    public String sell(Actor actor){
        Random random = new Random();
        if (random.nextDouble() <= 0.5) {
            actor.removeItemFromInventory(this);
            return actor + " sold " + this + " without being paid!";
        }
        actor.addBalance(this.sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this + " at its normal price (" + this.sellingPrice +" runes)";
        }

    @Override
    public int getSellingPrice() {
        return this.sellingPrice;
    }


    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }
}


