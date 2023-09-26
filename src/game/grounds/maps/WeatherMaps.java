package game.grounds.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import game.Status;

public abstract class WeatherMaps extends GameMap {
    private Status weather;


    public WeatherMaps(GroundFactory groundFactory, char groundChar, int width, int height){
        super(groundFactory, groundChar, width, height);
    }

    public void setWeather(Status weather){
        this.weather = weather;
    }

    public Status getWeather(){
        return this.weather;
    }
}
