package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be upgraded.
 * @author Darin Park
 *
 */
public interface Upgradeable {

    /**
     * Upgrades the item for the provided actor.
     *
     * @param actor the actor that get their item upgraded
     * @return a string indicating the result of the upgrade
     */
    String upgrade(Actor actor);

    /**
     * Retrieves the price for upgrading the item.
     *
     * @return the price for upgrading the item
     */
    int getUpgradingPrice();

    /**
     * Checks if the item is able to be upgraded.
     *
     * @return true if the item can be upgraded, false otherwise
     */
    boolean ableToUpgrade();
}
