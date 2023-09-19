package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actions.ConsumeAction;
/**
 * Class representing a refreshing flask item that can be consumed by an actor to restore stamina.
 */
public class RefreshingFlask extends ConsumableItem{

    /**
     * Constructor for the RefreshingFlask class.
     *
     */
    public RefreshingFlask(){
        super("Refreshing flask", 'u', true);
        setModifiedAttribute(BaseActorAttributes.STAMINA);
    }

    /**
     * Generates a list of allowable actions for the owner of this refreshing flask, which includes a "Consume" action.
     *
     * @param owner The actor who owns this refreshing flask.
     * @return An ActionList containing allowable actions for the owner.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    /**
     * Consumes the refreshing flask, increasing the actor's stamina and returning the amount of stamina restored.
     *
     * @param actor The actor consuming the refreshing flask.
     * @return An integer value representing the amount of stamina restored.
     */
    @Override
    public void consume(Actor actor) {
        int buffedPoints = (int) (0.2 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA));
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, buffedPoints);
        setBuffedPoints(buffedPoints);
    }

}
