package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MonologueAction;
import game.monologues.MonologueCapable;
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
 * A class representing an Isolated Traveller actor in the game that can buy and sell item
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class IsolatedTraveller extends Actor implements MonologueCapable {
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

    /**
     * Adds a new monologue option for the blacksmith.
     *
     * @param newMonologue the new monologue string to be added
     */
    public void addMonologue(String newMonologue){
        this.monologueOptions.add(newMonologue);
    }

    /**
     * Removes a monologue option for the blacksmith.
     *
     * @param removedMonologue the monologue string to be removed
     */
    public void removeMonologue(String removedMonologue){
        this.monologueOptions.remove(removedMonologue);
    }


    /**
     * Generates a monologue for the player based on certain conditions.
     *
     * @param player the player (Actor) for whom the monologue is generated
     * @return a string representing the generated monologue
     */
    public String generateMonologue(Actor player){
        if(player.hasCapability(Status.HAS_GIANT_HAMMER)){
            addMonologue("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
            if(player.hasCapability(Status.DEFEATED_ABXERVYER)){
                removeMonologue("You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");
                addMonologue("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");
            }
        }
        Random random = new Random();
        String randomizeMonologue = monologueOptions.get(random.nextInt(monologueOptions.size()));
        return randomizeMonologue;
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
        actionList.add(new MonologueAction(this));
        for (Purchasable purchasable : this.itemInventory) {
            actionList.add(new PurchaseAction(purchasable,this));
        }
        return actionList;
    }
}

