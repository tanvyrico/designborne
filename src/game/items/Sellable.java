package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Sellable {
    String sell(Actor actor);

    int getSellingPrice();
}


