package game.items.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.items.Sellable;

public class GiantHammer extends SkilledWeapon implements Sellable {
    private int sellingPrice = 250;
    /**
     * Constructor for the SkilledWeapon class.
     */
    public GiantHammer() {
        super("Giant Hammer",'P', 160, "slams",90,0, false);
        addCapability(Status.AOE_POSSIBLE);
    }


    @Override
    public String sell(Actor actor) {
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }
}
