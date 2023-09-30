package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.capabilities.Ability;
import game.actions.ConsumeAction;
import game.actions.PickUpRunesAction;

public class Runes extends Item implements Consumable {
    private int quantity;
    public Runes(int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
        this.addCapability(Ability.INCREASE_BALANCE);
    }

    public int getQuantity(){
        return this.quantity;
    }

    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpRunesAction(this);
        return null;
    }

    @Override
    public String consume(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " increases " + actor + " balance by " + this.quantity;
    }

    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }
}
