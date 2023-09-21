package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

public class PurchaseAction extends Action {
    private Purchasable purchasable;
    public PurchaseAction(Purchasable purchasable){
        this.purchasable = purchasable;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return this.purchasable.purchase(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.purchasable;
    }
}
