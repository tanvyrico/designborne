package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;
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
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) 0.01 * actor.getAttributeMaximum(BaseActorAttributes.STAMINA));
    }

//    @Override
//    public ActionList allowableActions(Actor owner) {
//        ActionList actionList = new ActionList();
//        ConsumeAction consumeAction = new ConsumeAction(this);
//        actionList.add(consumeAction);
//        return actionList;
//    }

}

