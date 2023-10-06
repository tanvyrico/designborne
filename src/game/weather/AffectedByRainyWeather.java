package game.weather;

/**
 * An interface representing objects that are affected by rainy weather conditions in the game.
 * Classes implementing this interface should define the modifications or behavior changes
 * that occur when rainy weather is in effect.
 */
public interface AffectedByRainyWeather {

    /**
     * Specifies the modifications or behavior changes that occur when rainy weather is in effect.
     *
     * @return A message describing the modifications or behavior changes due to rainy weather.
     */
    String rainyWeatherModifications();

}
