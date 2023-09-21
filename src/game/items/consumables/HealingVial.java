package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.Ability;
import game.actions.ConsumeAction;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * Class representing a healing vial item that can be consumed by an actor to restore health.
 */
public class HealingVial extends Item implements Consumable, Purchasable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private int purchasePrice = 100;
    private int sellingPrice = 35;

    /**
     * Constructor for the HealingVial class.
     *
     */
    public HealingVial(){
        super("Healing vial", 'a', true);
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


    public String purchase(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.25) {
            this.purchasePrice = (int) (this.purchasePrice * 1.5);
        }
        if (actor.getBalance() >= this.purchasePrice){
            actor.deductBalance(this.purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }
        return "purchase failed!";
    }

    public String sell(Actor actor){
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            this.sellingPrice = (int) (this.sellingPrice * 2);
        }
        actor.addBalance(this.sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }
}
