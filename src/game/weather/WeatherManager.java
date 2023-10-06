package game.weather;

import java.util.ArrayList;
/**
 * The WeatherManager class manages the current weather conditions in the game and
 * applies weather-related effects to entities affected by weather changes.
 * @author Group6
 * Modified by: Group6
 */
public class WeatherManager {

    private static Weather weather;

    private static ArrayList<AffectedByWeather> affectedByWeatherEntities = new ArrayList<>();

    /**
     * Adds an entity to the list of entities affected by weather conditions.
     *
     * @param affectedByWeather The entity affected by weather.
     */
    public static void addAffectedByWeather(AffectedByWeather affectedByWeather){
        affectedByWeatherEntities.add(affectedByWeather);
    }

    /**
     * Removes an entity from the list of entities affected by weather conditions.
     *
     * @param affectedByWeather The entity affected by weather.
     */
    public static void removeAffectedByWeather(AffectedByWeather affectedByWeather){
        affectedByWeatherEntities.remove(affectedByWeather);
    }

    /**
     * Runs weather-related modifications on affected entities based on the current weather.
     *
     * @return A string containing the modifications applied to affected entities.
     */
    public static String executeWeatherModifications(){
        String result = "";
        if (weather == Weather.SUNNY) {
            for (AffectedByWeather affectedByWeather : affectedByWeatherEntities) {
                result += affectedByWeather.sunnyWeatherModifications() + "\n";
            }
        }
        else if (weather == Weather.RAINY){
            for (AffectedByWeather affectedByWeather : affectedByWeatherEntities) {
                result += affectedByWeather.rainyWeatherModifications() + "\n";
            }
        }
        return result;
    }

    /**
     * Sets the current weather.
     *
     * @param newWeather The new weather condition to set.
     * @return A message indicating the new weather condition.
     */
    public static String setWeather(Weather newWeather){
        weather = newWeather;
        return "The weather is now " + newWeather;
    }
}
