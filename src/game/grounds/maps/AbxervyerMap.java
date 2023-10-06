package game.grounds.maps;

import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.List;

/**
 * A specialized GameMap representing the AbxervyerMap with weather effects.
 * This map is designed to include weather conditions such as sunny or rainy.
 */
public class AbxervyerMap extends WeatherMaps{

    /**
     * Constructor for the AbxervyerMap class.
     *
     * @param groundFactory The factory used to create Ground objects for the map.
     * @param lines         The list of strings representing the map layout.
     */
    public AbxervyerMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }
}
