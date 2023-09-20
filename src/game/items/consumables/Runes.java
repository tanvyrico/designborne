package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.Ability;
import game.actions.ConsumeAction;
import game.actions.PickUpRunesAction;
import game.items.consumables.ConsumableItem;

public class Runes extends ConsumableItem {
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
    public void consume(Actor actor) {
        actor.addBalance(this.quantity);
        setBuffedPoints(this.quantity);
    }
}
