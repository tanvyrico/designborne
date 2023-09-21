package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
import game.actions.ConsumeAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a healing vial item that can be consumed by an actor to restore health.
 */
public class HealingVial extends Item implements Consumable, Purchasable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private int purchasePrice = 35;
    private int sellingPrice = 100;

    /**
     * Constructor for the HealingVial class.
     *
     */
    public HealingVial(){
        super("Healing vial", 'a', true);
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
    }



    /**
     * Consumes the healing vial, increasing the actor's health and returning the amount of health restored.
     *
     * @param actor The actor consuming the healing vial.
     * @return An integer value representing the amount of health restored.
     */
    @Override
    public String consume(Actor actor) {
        int buffedPoints = (int) (0.1 * actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, buffedPoints);
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


    public String sell(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.25) {
            this.sellingPrice = (int) (this.sellingPrice * 1.5);
        }
        if (actor.getBalance() >= this.sellingPrice){
            actor.deductBalance(this.sellingPrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }
        return "purchase failed!";
    }

    public String purchase(Actor actor){
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            this.purchasePrice = this.purchasePrice * 2;
        }
        actor.addBalance(this.purchasePrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new PurchaseAction(this));
        }
        return actionList;
    }
}
