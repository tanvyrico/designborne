package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

public class SellAction extends Action {
    private Sellable sellable;
    public SellAction(Sellable sellable){
        this.sellable = sellable;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return this.sellable.sell(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.sellable;
    }
}

