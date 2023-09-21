package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public interface Purchasable {
    String purchase(Actor actor);

    Item getItem(Purchasable purchasable);
}
