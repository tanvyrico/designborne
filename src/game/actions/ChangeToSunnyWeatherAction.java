package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.weather.AffectedBySunnyWeather;

public class ChangeToSunnyWeatherAction extends Action {
    private AffectedBySunnyWeather entity;

    public ChangeToSunnyWeatherAction(AffectedBySunnyWeather entity){
        this.entity = entity;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.entity.sunnyWeatherModifications();
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
