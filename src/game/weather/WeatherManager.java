package game.weather;

import java.util.ArrayList;

public class WeatherManager {

    private static Weather weather;

    private static ArrayList<AffectedByWeather> affectedByWeatherEntities = new ArrayList<>();


    public static void addAffectedByWeather(AffectedByWeather affectedByWeather){
        affectedByWeatherEntities.add(affectedByWeather);
    }
    public static void removeAffectedByWeather(AffectedByWeather affectedByWeather){
        affectedByWeatherEntities.remove(affectedByWeather);
    }

    public static String run(){
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

    public static String setWeather(Weather newWeather){
        weather = newWeather;
        return "The weather is now " + newWeather;
    }
}
