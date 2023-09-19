package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Runes;

public class PickUpRunesAction extends PickUpAction {
    private final Runes runes;
    public PickUpRunesAction(Runes runes) {
        super(runes);
        this.runes = runes;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(runes);
        actor.addBalance(runes.getQuantity());
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + runes.getQuantity() + runes;
    }

}
