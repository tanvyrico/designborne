package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
import game.Status;
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


    @Override
    public String consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,(int) Math.round(0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return actor + " drinks from " + this + " and " + this + " restores " + 1 +  BaseActorAttributes.HEALTH + " and " + Math.round(0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) + BaseActorAttributes.STAMINA;
    }

    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        if (location.containsAnActor() && location.getActor().hasCapability((Status.HOSTILE_TO_ENEMY))){
            ConsumeAction consumeAction = new ConsumeAction(this);
            actionList.add(consumeAction);
        }
        return actionList;
    }

    public void tick(Location location) {
        Actor actor = location.getActor();
        if (location.containsAnActor() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

        }
    }
    public String toString() {
        return "puddle";
    }

}

