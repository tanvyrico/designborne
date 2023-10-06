package game.grounds.maps;

import edu.monash.fit2099.engine.positions.GroundFactory;

import java.util.List;

/**
 * A specialized GameMap representing the Ancient Woods Map with weather effects.
 * This map is designed to include weather conditions such as sunny or rainy.
 */
public class AncientWoodsMap extends WeatherMaps{

    /**
     * Constructor for the AncientWoodsMap class.
     *
     * @param groundFactory The factory used to create Ground objects for the map.
     * @param lines         The list of strings representing the map layout.
     */
    public AncientWoodsMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }
}
