package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Upgradeable {
    String upgrade(Actor actor);
    int getUpgradingPrice();
    boolean ableToUpgrade();
}
