package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actions.ConsumeAction;
/**
 * Class representing a healing vial item that can be consumed by an actor to restore health.
 */
public class HealingVial extends ConsumableItem{

    /**
     * Constructor for the HealingVial class.
     *
     */
    public HealingVial(){
        super("Healing vial", 'a', true);
        setModifiedAttribute(BaseActorAttributes.HEALTH);
    }



    /**
     * Consumes the healing vial, increasing the actor's health and returning the amount of health restored.
     *
     * @param actor The actor consuming the healing vial.
     * @return An integer value representing the amount of health restored.
     */
    @Override
    public void consume(Actor actor) {
        int buffedPoints = (int) (0.1 * actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, buffedPoints);
        setBuffedPoints(buffedPoints);
        actor.removeItemFromInventory(this);
    }
}
