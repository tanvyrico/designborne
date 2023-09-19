package game.items.consumables;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
/**
 * An abstract class representing a consumable item that can be used by an actor. Extends the Item class.
 */
public abstract class ConsumableItem extends Item implements Consumable {
    BaseActorAttributes modifiedAttribute;

    /**
     * Constructor for the ConsumableItem class.
     *
     * @param name        The name of this item.
     * @param displayChar The character to use to represent this item if it is on the ground.
     * @param portable    True if and only if the item can be picked up.
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Gets the modified attribute associated with this consumable item.
     *
     * @return The BaseActorAttributes representing the modified attribute.
     */
    public BaseActorAttributes getModifiedAttribute() {
        return this.modifiedAttribute;
    }
}
