package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.MonologueAction;
import game.monologues.MonologueCapable;
import game.capabilities.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * A class representing a Blacksmith actor that can upgrade weapon
 * @author Darin Park
 * modified by Group 6
 */
public class Blacksmith extends Actor implements MonologueCapable {

    private ArrayList<String> monologueOptions = new ArrayList<>(Arrays.asList("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.",
            "It’s dangerous to go alone. Take my creation with you on your adventure!",
            "Ah, it’s you. Let’s get back to make your weapons stronger.",
            "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!"));

    /**
     * Constructor for the Blacksmith class.
     */
    public Blacksmith() {
        super("Blacksmith", 'B', 2147483647);
        this.addCapability(Status.UPGRADE_ITEMS_WEAPONS);
        this.addBalance(0);
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
        if(player.hasCapability(Status.DEFEATED_ABXERVYER)){
            addMonologue("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");
            removeMonologue("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");
        }
        if(player.hasCapability(Status.HAS_GREAT_KNIFE)){
            addMonologue("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
        }
        Random random = new Random();
        String randomizedMonologue = monologueOptions.get(random.nextInt(monologueOptions.size()));
        return randomizedMonologue;
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        actionList.add(new MonologueAction(this));
        return actionList;
    }

    /**
     * Overrides the playTurn method to make the blacksmith perform no action during its turn.
     *
     * @param actions   the list of available actions
     * @param lastAction the last action performed
     * @param map       the game map
     * @param display   the game display
     * @return a DoNothingAction to signify the blacksmith performing no action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
