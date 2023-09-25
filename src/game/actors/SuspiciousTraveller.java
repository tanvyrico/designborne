package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;
import game.items.consumables.HealingVial;
import game.items.consumables.RefreshingFlask;
import game.items.weapons.BroadSword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuspiciousTraveller extends Actor {
    private final List<Purchasable> itemInventory = new ArrayList<>(Arrays.asList(new HealingVial(), new RefreshingFlask(), new BroadSword()));

    /**
     * The constructor of the Actor class.
     */
    public SuspiciousTraveller() {
        super("Suspicious Traveller", 'ඞ', 1000);
        this.addCapability(Status.TRADER);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FRIENDLY_TO_ENEMY);
        this.addBalance(9999999);
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    for (Purchasable purchasable : this.itemInventory) {
                        actionList.add(new PurchaseAction(purchasable));
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

