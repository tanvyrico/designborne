package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.actions.ConsumeAction;
import game.items.consumables.Consumable;

/**
 * Class representing a puddle ground on the game map.
 */
public class Puddle extends Ground implements Consumable {

    /**
     * Constructor for the Puddle class.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Allows an actor to consume the puddle, restoring health and stamina.
     *
     * @param actor The actor consuming the puddle.
     * @return A message describing the effects of consuming the puddle.
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,(int) Math.round(0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return actor + " drinks from " + this + " and " + this + " restores " + 1 +  BaseActorAttributes.HEALTH + " and " + Math.round(0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) + BaseActorAttributes.STAMINA;
    }

    /**
     * Generates a list of allowable actions for an actor at the puddle location.
     *
     * @param actor     The actor at the puddle location.
     * @param location  The location of the puddle.
     * @param direction The direction from which the actor approaches the puddle.
     * @return A list of allowable actions for the actor at the puddle location.
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        if (location.containsAnActor() && location.getActor().hasCapability((Status.HOSTILE_TO_ENEMY))){
            ConsumeAction consumeAction = new ConsumeAction(this);
            actionList.add(consumeAction);
        }
        return actionList;
    }

    /**
     * Performs actions when the game map is ticked.
     *
     * @param location The location of the puddle.
     */
    public void tick(Location location) {
        Actor actor = location.getActor();
        if (location.containsAnActor() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

        }
    }

    /**
     * Returns a string representation of the puddle.
     *
     * @return A string representing the puddle.
     */
    public String toString() {
        return "puddle";
    }

}

