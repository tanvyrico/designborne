package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Ability;

/**
 * A class representing an old key item that can be used to open gates.
 * The key has the capability to open gates with the Ability.OPEN_GATE capability.
 */
public class OldKey extends Item {

    /**
     * Constructor for the OldKey class.
     *
     * @param name        The name of this Item.
     * @param displayChar The character to use to represent this item if it is on the ground.
     * @param portable    True if and only if the Item can be picked up.
     */
    public OldKey(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(Ability.OPEN_GATE);
    }
}
