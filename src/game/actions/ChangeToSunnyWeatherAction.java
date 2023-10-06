package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weather.AffectedBySunnyWeather;

/**
 * An Action that changes the weather to sunny conditions for an entity that is affected by sunny weather.
 */

public class ChangeToSunnyWeatherAction extends Action {
    private AffectedBySunnyWeather entity;

    /**
     * Constructor for the ChangeToSunnyWeatherAction class.
     *
     * @param entity The entity that will be affected by sunny weather.
     */
    public ChangeToSunnyWeatherAction(AffectedBySunnyWeather entity){
        this.entity = entity;
    }

    /**
     * Executes the action to change the weather to sunny conditions for the affected entity.
     *
     * @param actor The actor performing the action (not used in this action).
     * @param map   The GameMap where the action is executed (not used in this action).
     * @return A message indicating the sunny weather modifications applied to the entity.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.entity.sunnyWeatherModifications();
    }

    /**
     * Provides a menu description for the action (not used in this action).
     *
     * @param actor The actor for whom the menu description is generated (not used in this action).
     * @return Always returns null since there is no menu description for this action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
