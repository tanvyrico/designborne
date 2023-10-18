package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Upgradeable;

public class UpgradeAction extends Action {
    private Upgradeable upgradeable;

    public UpgradeAction(Upgradeable upgradeable){
        this.upgradeable = upgradeable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.upgradeable.ableToUpgrade()){
            return this.upgradeable.upgrade(actor);
        }else{
            return this.upgradeable + " cannot presently be upgraded.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + this.upgradeable + " with " + this.upgradeable.getUpgradingPrice() + " runes";
    }
}
