package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Monologist;
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
import java.util.Random;

/**
 * A class representing an Isolated Traveller actor in the game.
 * Isolated Travellers are friendly actors capable of trading with hostile actors.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class IsolatedTraveller extends Actor implements Monologist {
    private final List<Purchasable> itemInventory = new ArrayList<>(Arrays.asList(new HealingVial(), new RefreshingFlask(), new BroadSword(), new GreatKnife()));

    private ArrayList<String> monologueOptions = new ArrayList<>(Arrays.asList("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.",
            "It’s dangerous to go alone. Take my creation with you on your adventure!",
            "Ah, it’s you. Let’s get back to make your weapons stronger.",
            "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!"));


    /**
     * Constructor for the IsolatedTraveller class.
     */
    public IsolatedTraveller() {
        super("Isolated Traveller", 'ඞ', 2147483647);
        this.addCapability(Status.TRADER);
        this.addCapability(Status.SUSPICIOUS);
        this.addBalance(9999999);
    }

    /**
     * Determines the actions that the Isolated Traveller can perform on its turn.
     *
     * @param actions    A collection of possible actions for the Isolated Traveller.
     * @param lastAction The action the Isolated Traveller took last turn.
     * @param map        The GameMap containing the Isolated Traveller.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action to be performed during this turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public String generateMonologue(Actor player){
        if(player.hasCapability(Status.DEFEATED_ABXERVYER)){
            this.monologueOptions.remove("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");
            this.monologueOptions.add("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");
        }
        if(player.hasCapability(Status.HAS_GREAT_KNIFE)){
            this.monologueOptions.add("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
        }
        Random random = new Random();
        return monologueOptions.get(random.nextInt(monologueOptions.size()));
    }


    /**
     * Retrieves the list of allowable actions for the Isolated Traveller.
     *
     * @param otherActor The actor interacting with the Isolated Traveller.
     * @param direction  The direction from which the interaction is taking place.
     * @param map        The GameMap in which the interaction is occurring.
     * @return An ActionList containing the valid actions the Isolated Traveller can perform.
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

                }
            }
        }
        return actionList;
    }
}

