package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.capabilities.Ability;

/**
 * A class representing an old key item that can be used to open gates.
 * The key has the capability to open gates with the Ability.OPEN_GATE capability.
 */
public class OldKey extends Item{

    /**
     * Constructor for the OldKey class.
     *
     */
    public OldKey() {
        super("Old key", '-', true);
        this.addCapability(Ability.OPEN_GATE);
    }
}
