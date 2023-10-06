package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.npcs.enemies.bosses.AbxervyerForestWatcher;
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
import game.items.consumables.Bloodberry;
import game.items.weapons.BroadSword;
import game.items.weapons.GiantHammer;
import game.utility.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Group6
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

        List<String> abxervyerBossRoom = Arrays.asList(
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

        GameMap abxervyerMap = new GameMap(groundFactory, abxervyerBossRoom);
        world.addGameMap(abxervyerMap);



        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("The Abstracted One", '@', 150, 200);
        IsolatedTraveller isolatedTraveller = new IsolatedTraveller();
        world.addPlayer(player, gameMap.at(29, 5));
        ancientWoodsMap.at(20, 3).addActor(isolatedTraveller);

        gameMap.at(35, 0).setGround(new Gate(burialGroundMap, burialGroundMap.at(29,7), "The Burial Ground"));
        burialGroundMap.at(31, 5).setGround(new Gate(ancientWoodsMap, gameMap.at(29,7), "The Ancient Woods"));
        ancientWoodsMap.at(30, 0).setGround(new Gate(abxervyerMap, abxervyerMap.at(0,0), "Abxervyer, The Forest Watcher's Battle Room"));

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

        ancientWoodsMap.at(21, 10).addItem(new Bloodberry());
        ancientWoodsMap.at(20, 5).addItem(new Bloodberry());
        ancientWoodsMap.at(10,9).addItem(new Bloodberry());

        abxervyerMap.at(27, 8).setGround(new Hut(forestKeeper));
        abxervyerMap.at(35, 3).setGround(new Hut(forestKeeper));
        abxervyerMap.at(18, 7).setGround(new Hut(forestKeeper));

        abxervyerMap.at(29, 10).setGround(new Bush(redWolf));
        abxervyerMap.at(37, 5).setGround(new Bush(redWolf));
        abxervyerMap.at(20, 9).setGround(new Bush(redWolf));

        GiantHammer giantHammer = new GiantHammer();
        abxervyerMap.at(29, 2).addItem(giantHammer);

        AbxervyerForestWatcher abxervyerForestWatcher = new AbxervyerForestWatcher(ancientWoodsMap);
        abxervyerMap.at(14,8).addActor(abxervyerForestWatcher);

        world.run();
    }
}
