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
import game.items.weapons.GreatKnife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsolatedTraveller extends Actor {
    private final List<Purchasable> itemInventory = new ArrayList<>(Arrays.asList(new HealingVial(), new RefreshingFlask(), new BroadSword(), new GreatKnife()));

    public IsolatedTraveller() {
        super("Isolated Traveller", 'ඞ', 2147483647);
        this.addCapability(Status.TRADER);
        this.addCapability(Status.SUSPICIOUS);
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

