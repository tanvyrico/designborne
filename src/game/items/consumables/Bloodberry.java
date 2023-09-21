package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.items.Purchasable;
import game.items.Sellable;

public class Bloodberry extends Item implements Consumable, Purchasable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private int purchasePrice = 10;

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
    public String purchase(Actor actor) {
        actor.addBalance(purchasePrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

    public Item getItem(Purchasable purchasable){
        return this;
    }
}
