package game.weather;
/**
 * The AffectedByWeather interface defines methods that entities can implement
 * to specify modifications that occur when affected by different weather conditions.
 * @author Group6
 * Modified by: Group6
 */
public interface AffectedByWeather {

    /**
     * Specifies modifications that occur when the entity is affected by sunny weather.
     *
     * @return A string describing the modifications applied during sunny weather.
     */
    String sunnyWeatherModifications();

    /**
     * Specifies modifications that occur when the entity is affected by rainy weather.
     *
     * @return A string describing the modifications applied during rainy weather.
     */
    String rainyWeatherModifications();

}
