package game.weather;

/**
 * An interface representing objects that are affected by sunny weather conditions in the game.
 * Classes implementing this interface should define the modifications or behavior changes
 * that occur when sunny weather is in effect.
 */
public interface AffectedBySunnyWeather {

    /**
     * Specifies the modifications or behavior changes that occur when sunny weather is in effect.
     *
     * @return A message describing the modifications or behavior changes due to sunny weather.
     */
    String sunnyWeatherModifications();
}
