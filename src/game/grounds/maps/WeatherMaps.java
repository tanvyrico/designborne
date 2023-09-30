package game.grounds.maps;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.GroundFactory;
import game.Status;

import java.util.List;

public abstract class WeatherMaps extends GameMap {

    public WeatherMaps(GroundFactory groundFactory, List<String> lines){
        super(groundFactory, lines);
    }

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
