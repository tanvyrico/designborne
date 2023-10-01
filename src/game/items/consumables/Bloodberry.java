package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.items.Sellable;

public class Bloodberry extends Item implements Consumable, Sellable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private int sellingPrice = 10;

    public Bloodberry() {
        super("Bloodberry", '*', true);
    }
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 5);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + 5 + " points.";
    }

    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    @Override
    public String sell(Actor actor) {
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

