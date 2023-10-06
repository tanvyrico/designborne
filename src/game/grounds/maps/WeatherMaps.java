package game.grounds.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GroundFactory;
import game.capabilities.Status;

import java.util.List;

/**
 * An abstract class representing a GameMap with weather effects.
 * WeatherMaps can have different weather conditions, such as sunny or rainy, applied to their Ground objects.
 */
public abstract class WeatherMaps extends GameMap {

    /**
     * Constructor for the WeatherMaps class.
     *
     * @param groundFactory The factory used to create Ground objects for the map.
     * @param lines         The list of strings representing the map layout.
     */
    public WeatherMaps(GroundFactory groundFactory, List<String> lines){
        super(groundFactory, lines);
    }

    /**
     * Sets the weather condition for all Ground objects in the map.
     *
     * @param weather The weather condition to be set (e.g., sunny or rainy).
     */
    public void setWeather(Enum<Status> weather){
        for (int y : heights) {
            for (int x : widths) {
                Ground ground = this.at(x, y).getGround();
                if (ground.hasCapability(Status.SUNNY)){
                    ground.removeCapability(Status.SUNNY);
                }
                else if (ground.hasCapability(Status.RAINY)){
                    ground.removeCapability(Status.RAINY);
                }
                ground.addCapability(weather);
            }
        }
    }
}
