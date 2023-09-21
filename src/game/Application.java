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
import game.grounds.spawners.Bush;
import game.grounds.spawners.Graveyard;
import game.grounds.spawners.Hut;
import game.items.weapons.BroadSword;
import game.items.weapons.GreatKnife;
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
                "...~~~~~~~~....+++...........~.............................",
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

        List<String> Abxervyer = Arrays.asList(
                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~");

        GameMap AbxervyerMap = new GameMap(groundFactory, Abxervyer);
        world.addGameMap(AbxervyerMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("The Abstracted One", '@', 1500000000, 200);
        world.addPlayer(player, gameMap.at(29, 5));

        gameMap.at(30, 6).setGround(new Gate(burialGroundMap, burialGroundMap.at(29,7), "The Burial Ground"));
        burialGroundMap.at(31, 5).setGround(new Gate(ancientWoodsMap, gameMap.at(29,7), "The Ancient Woods"));
        ancientWoodsMap.at(30, 0).setGround(new Gate(gameMap, gameMap.at(28,6), "The Abandoned Village"));

        BroadSword broadSword = new BroadSword();
        GreatKnife greatKnife = new GreatKnife();
        gameMap.at(29,6).addItem(broadSword);
        gameMap.at(27,6).addItem(greatKnife);

        WanderingUndead wanderingUndead = new WanderingUndead();
        HollowSoldier hollowSoldier = new HollowSoldier();
        ForestKeeper forestKeeper = new ForestKeeper();
        RedWolf redWolf = new RedWolf();

        gameMap.at(27, 8).setGround(new Graveyard(wanderingUndead, 0.25));
        gameMap.at(35, 3).setGround(new Graveyard(wanderingUndead, 0.25));
        gameMap.at(18, 7).setGround(new Graveyard(wanderingUndead, 0.25));

        burialGroundMap.at(27, 8).setGround(new Graveyard(hollowSoldier, 0.1));
        burialGroundMap.at(35, 3).setGround(new Graveyard(hollowSoldier, 0.1));
        burialGroundMap.at(18, 7).setGround(new Graveyard(hollowSoldier, 0.1));

        ancientWoodsMap.at(27, 8).setGround(new Hut(forestKeeper, 0.15));
        ancientWoodsMap.at(35, 3).setGround(new Hut(forestKeeper, 0.15));
        ancientWoodsMap.at(18, 7).setGround(new Hut(forestKeeper, 0.15));

        ancientWoodsMap.at(29, 10).setGround(new Bush(redWolf, 0.15));
        ancientWoodsMap.at(37, 5).setGround(new Bush(redWolf, 0.15));
        ancientWoodsMap.at(20, 9).setGround(new Bush(redWolf, 0.15));

        world.run();
    }
}
