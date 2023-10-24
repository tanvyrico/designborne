package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import game.monologues.MonologueCapable;

public class MonologueAction extends Action {
    private MonologueCapable monologist;

    public MonologueAction(MonologueCapable monologist) {
        this.monologist = monologist;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.monologist + ": " + this.monologist.generateMonologue(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + this.monologist;
    }
}

