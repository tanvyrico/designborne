package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class Bloodberry extends Item implements Consumable{
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;

    public Bloodberry() {
        super("Bloodberry", '*', true);
    }
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 5);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + 5 + " points.";
    }

    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

}
