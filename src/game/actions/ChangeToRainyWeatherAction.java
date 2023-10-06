package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weather.AffectedByRainyWeather;

/**
 * An Action that changes the weather to rainy conditions for an entity that is affected by rainy weather.
 */
public class ChangeToRainyWeatherAction extends Action {
    private AffectedByRainyWeather entity;

    /**
     * Constructor for the ChangeToRainyWeatherAction class.
     *
     * @param entity The entity that will be affected by rainy weather.
     */
    public ChangeToRainyWeatherAction(AffectedByRainyWeather entity){
        this.entity = entity;
    }

    /**
     * Executes the action to change the weather to rainy conditions for the affected entity.
     *
     * @param actor The actor performing the action (not used in this action).
     * @param map   The GameMap where the action is executed (not used in this action).
     * @return A message indicating the rainy weather modifications applied to the entity.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.entity.rainyWeatherModifications();
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
