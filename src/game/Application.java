package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.enemies.ForestKeeper;
import game.actors.enemies.HollowSoldier;
import game.actors.Player;
import game.actors.enemies.RedWolf;
import game.actors.enemies.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.items.weapons.BroadSword;
import game.utility.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Lim Hung Xuan
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~....+++.........................................",
                "....~~~~~........+++++++..................###..##..........",
                "~~~~~~~..............+++..................#___..#..........",
                "~~~~~~.................++.................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);


        List<String> burialGround = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        GameMap burialGroundMap = new GameMap(groundFactory, burialGround);
        world.addGameMap(burialGroundMap);


        List<String> ancientWoods = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

        GameMap ancientWoodsMap = new GameMap(groundFactory, ancientWoods);
        world.addGameMap(ancientWoodsMap);


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("The Abstracted One", '@', 150, 200);
        world.addPlayer(player, gameMap.at(29, 5));

        gameMap.at(29, 0).setGround(new Gate(burialGroundMap, burialGroundMap.at(29,7), "The Burial Ground"));
        burialGroundMap.at(31, 5).setGround(new Gate(gameMap, gameMap.at(29,7), "The Abandoned Village"));

        burialGroundMap.at(25,5).setGround(new Gate(ancientWoodsMap, ancientWoodsMap.at(29,7), "The Ancient Woods"));
        ancientWoodsMap.at(10,3).setGround(new Gate(burialGroundMap, burialGroundMap.at(29,7), "The Burial Ground"));

        BroadSword broadSword = new BroadSword();
        gameMap.at(29,6).addItem(broadSword);

        WanderingUndead wanderingUndead = new WanderingUndead();
        HollowSoldier hollowSoldier = new HollowSoldier();
        ForestKeeper forestKeeper = new ForestKeeper();
        RedWolf redWolf = new RedWolf();

        gameMap.at(27, 8).setGround(new Graveyard(wanderingUndead));
        gameMap.at(35, 3).setGround(new Graveyard(wanderingUndead));
        gameMap.at(18, 7).setGround(new Graveyard(wanderingUndead));

        burialGroundMap.at(27, 8).setGround(new Graveyard(hollowSoldier));
        burialGroundMap.at(35, 3).setGround(new Graveyard(hollowSoldier));
        burialGroundMap.at(18, 7).setGround(new Graveyard(hollowSoldier));

        ancientWoodsMap.at(27, 8).setGround(new Hut(forestKeeper));
        ancientWoodsMap.at(35, 3).setGround(new Hut(forestKeeper));
        ancientWoodsMap.at(18, 7).setGround(new Bush((redWolf)));

        world.run();
    }
}
