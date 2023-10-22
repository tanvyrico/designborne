package game.actors.npcs.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;

public class Blacksmith extends Actor{
    public Blacksmith() {
        super("Blacksmith", 'B', 2147483647);
        this.addCapability(Status.UPGRADE_ITEMS_WEAPONS);
        this.addBalance(0);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
