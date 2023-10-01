package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.actions.PurchaseAction;
import game.items.Purchasable;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.weapons.BroadSword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class representing a Suspicious Traveller actor in the game.
 * Suspicious Travellers are actors that have the capability to trade with other actors.
 * They have an inventory of purchasable items and can perform trading actions with hostile actors.
 * Suspicious Travellers are also capable of engaging in combat and can be both friendly and hostile to enemy actors.
 */
public class SuspiciousTraveller extends Actor {
    private final List<Purchasable> itemInventory = new ArrayList<>(Arrays.asList(new HealingVial(), new RefreshingFlask(), new BroadSword()));

    /**
     * Constructor for the SuspiciousTraveller class.
     * Initializes the name, display character, hit points, capabilities, and initial balance.
     */
    public SuspiciousTraveller() {
        super("Suspicious Traveller", 'ඞ', 1000);
        this.addCapability(Status.TRADER);
        this.addCapability(Status.SUSPICIOUS);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FRIENDLY_TO_ENEMY);
        this.addBalance(9999999);
    }

    /**
     * Determines the action to be performed by the Suspicious Traveller during its turn.
     *
     * @param actions    A collection of possible actions for the Suspicious Traveller.
     * @param lastAction The action the Suspicious Traveller took last turn.
     * @param map        The GameMap containing the Suspicious Traveller.
     * @param display    The I/O object to which messages may be written.
     * @return A DoNothingAction since the Suspicious Traveller does not perform any actions during its turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Retrieves the allowable trading actions for the Suspicious Traveller.
     *
     * @param otherActor The actor with which the Suspicious Traveller can trade.
     * @param direction  The direction in which the trade can take place.
     * @param map        The GameMap where the trade can occur.
     * @return A list of trading actions, including purchasing items from the Suspicious Traveller.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    for (Purchasable purchasable : this.itemInventory) {
                        actionList.add(new PurchaseAction(purchasable,this));
                    }
//                    for (Item item : otherActor.getItemInventory()) {
//                        for (Action action : item.allowableActions(otherActor, destination)) {
//                            actionList.add(action);
//                        }
                }
            }
        }

        return actionList;

    }
}

