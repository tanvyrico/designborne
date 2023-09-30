package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

public class PurchaseAction extends Action {
    private Purchasable purchasable;

    private Actor seller;

    public PurchaseAction(Purchasable purchasable,Actor seller){
        this.purchasable = purchasable;
        this.seller = seller;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return this.purchasable.purchase(actor,seller);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + this.purchasable + " for " + purchasable.getPurchasePrice(seller) + " runes";
    }
}

