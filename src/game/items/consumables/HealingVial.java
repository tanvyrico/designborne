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
     * @param name        The name of this item.
     * @param displayChar The character to use to represent this item if it is on the ground.
     * @param portable    True if and only if the item can be picked up.
     */
    public HealingVial(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.modifiedAttribute = BaseActorAttributes.HEALTH;
    }

    /**
     * Generates a list of allowable actions for the owner of this healing vial, which includes a "Consume" action.
     *
     * @param owner The actor who owns this healing vial.
     * @return An ActionList containing allowable actions for the owner.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    /**
     * Consumes the healing vial, increasing the actor's health and returning the amount of health restored.
     *
     * @param actor The actor consuming the healing vial.
     * @return An integer value representing the amount of health restored.
     */
    @Override
    public int consume(Actor actor) {
        int buffedPoints = (int) (0.1 * actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, buffedPoints);
        return buffedPoints;
    }
}
