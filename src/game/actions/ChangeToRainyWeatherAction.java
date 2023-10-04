package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weather.AffectedByRainyWeather;

public class ChangeToRainyWeatherAction extends Action {
    private AffectedByRainyWeather entity;

    public ChangeToRainyWeatherAction(AffectedByRainyWeather entity){
        this.entity = entity;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.entity.rainyWeatherModifications();
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
