package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.npcs.merchants.IsolatedTraveller;
import game.actors.npcs.enemies.ForestKeeper;
import game.actors.npcs.enemies.HollowSoldier;
import game.actors.Player;
import game.actors.npcs.enemies.RedWolf;
import game.actors.npcs.enemies.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.grounds.spawners.Bush;
import game.grounds.spawners.Graveyard;
import game.grounds.spawners.Hut;
import game.items.weapons.BroadSword;
import game.items.weapons.GiantHammer;
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
        IsolatedTraveller isolatedTraveller = new IsolatedTraveller();
        world.addPlayer(player, gameMap.at(29, 5));
        gameMap.at(28, 5).addActor(new IsolatedTraveller());
        ancientWoodsMap.at(20, 3).addActor(isolatedTraveller);

        gameMap.at(30, 6).setGround(new Gate(burialGroundMap, burialGroundMap.at(29,7), "The Burial Ground"));
        burialGroundMap.at(31, 5).setGround(new Gate(ancientWoodsMap, gameMap.at(29,7), "The Ancient Woods"));
        ancientWoodsMap.at(30, 0).setGround(new Gate(gameMap, gameMap.at(28,6), "The Abandoned Village"));

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
        ancientWoodsMap.at(18, 7).setGround(new Hut(forestKeeper));

        ancientWoodsMap.at(29, 10).setGround(new Bush(redWolf));
        ancientWoodsMap.at(37, 5).setGround(new Bush(redWolf));
        ancientWoodsMap.at(20, 9).setGround(new Bush(redWolf));

        AbxervyerMap.at(27, 8).setGround(new Hut(forestKeeper));
        AbxervyerMap.at(35, 3).setGround(new Hut(forestKeeper));
        AbxervyerMap.at(18, 7).setGround(new Hut(forestKeeper));

        AbxervyerMap.at(29, 10).setGround(new Bush(redWolf));
        AbxervyerMap.at(37, 5).setGround(new Bush(redWolf));
        AbxervyerMap.at(20, 9).setGround(new Bush(redWolf));

        GiantHammer giantHammer = new GiantHammer();
        gameMap.at(20,10).addItem(giantHammer);



        world.run();
    }
}
