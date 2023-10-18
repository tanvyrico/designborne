package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class UpgradeAction extends Action {
    private Upgradeable upgradeable;

    public UpgradeAction(Upgradeable upgradeable){
        this.Upgradeable = upgradeable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.upgradeable.upgrade(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + this.upgradeable + " with " + this.upgradeable.getSellingPrice() + " runes";
    }
}
