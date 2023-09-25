package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Purchasable {
    String purchase(Actor actor);
    void setPurchasePrice(Actor actor);

}
