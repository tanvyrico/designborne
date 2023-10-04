package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
<<<<<<< HEAD
import game.utility.FancyMessage;
import game.capabilities.Status;
=======
import game.capabilities.Status;
import game.utility.FancyMessage;
>>>>>>> TASK_5_Branch

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Lim Hung Xuan
 */
public class Player extends Actor {

    private final int intrinsicDamage = 15000;
    private final int hitRate = 80;


    /**
     * Constructor for the Player class.
     *
     * @param name        Name to call the player in the UI.
     * @param displayChar Character to represent the player in the UI.
     * @param hitPoints   Player's starting number of hit points.
     * @param stamina     Player's starting stamina points.
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.getIntrinsicWeapon();
        this.addBalance(99999);
    }

    /**
     * Determines the action to be performed by the player during its turn.
     *
     * @param actions    A collection of possible actions for the player.
     * @param lastAction The action the player took last turn.
     * @param map        The GameMap containing the player.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action to be performed during this turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (getAttribute(BaseActorAttributes.STAMINA) < this.getAttributeMaximum(BaseActorAttributes.STAMINA)) {
            this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) (this.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.01));
        }

        display.println(this.name);
        display.println("HP: " + getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        display.println("Stamina: " + getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA));
        display.println("Runes: " + this.getBalance());

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Handles the outcome when the player character becomes unconscious, typically due to falling into the void.
     *
     * @param map The GameMap where the player character fell into the void.
     * @return A message describing the outcome of the player character falling into the void.
     */
    public String unconscious(GameMap map) {
        Display display = new Display();
        map.removeActor(this);
        display.println(this + " fell into the void.");
        display.println(FancyMessage.YOU_DIED);
        return this + " fell into the void.";
    }

    /**
     * Handles the outcome when the player character becomes unconscious, typically due to being defeated by another actor.
     *
     * @param actor The actor that defeated the player character.
     * @param map   The GameMap where the player character was defeated.
     * @return A message describing the outcome of the player character's defeat.
     */
    public String unconscious(Actor actor, GameMap map) {
        Display display = new Display();
        map.removeActor(this);
        display.println(FancyMessage.YOU_DIED);
        return this + " met their demise at the hands of " + actor;
    }

    /**
     * Retrieves the intrinsic weapon used by the player character.
     *
     * @return An IntrinsicWeapon representing the player character's damage capability.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "bonks", hitRate);
    }

//    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//
//        ActionList actionList = new ActionList();
//
//        if (otherActor.hasCapability(Status.TRADER)) {
//            for (Item item : this.getItemInventory()) {
//                actionList.add(item.allowableActions(this, map.locationOf(this)));
//            }
//        }
//
//
//        return actionList;
//    }


}




