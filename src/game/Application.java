package game;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.npcs.enemies.*;
import game.actors.npcs.enemies.bosses.AbxervyerForestWatcher;
import game.actors.npcs.merchants.Blacksmith;
import game.actors.npcs.merchants.IsolatedTraveller;
import game.actors.Player;
import game.grounds.*;
import game.grounds.Void;
import game.grounds.spawners.Bush;
import game.grounds.spawners.Graveyard;
import game.grounds.spawners.Hut;
import game.items.OldKey;
import game.items.consumables.Bloodberry;
import game.items.consumables.HealingVial;
import game.items.consumables.Runes;
import game.items.weapons.BroadSword;
import game.items.weapons.GiantHammer;
import game.items.weapons.GreatKnife;
import game.utility.FancyMessage;
import game.utility.Map;

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

        GameMap gameMap = new GameMap(groundFactory, Map.ABANDONED_VILLAGE);
        world.addGameMap(gameMap);

        GameMap burialGroundMap = new GameMap(groundFactory, Map.BURIAL_GROUND);
        world.addGameMap(burialGroundMap);

        GameMap ancientWoodsMap = new GameMap(groundFactory, Map.ANCIENT_WOODS);
        world.addGameMap(ancientWoodsMap);

        GameMap abxervyerMap = new GameMap(groundFactory, Map.ABXERVYER_BOSS_ROOM);
        world.addGameMap(abxervyerMap);

        GameMap overgrownSanctuaryMap = new GameMap(groundFactory, Map.OVERGROWN_SANCTUARY);
        world.addGameMap(overgrownSanctuaryMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("The Abstracted One", '@', 150, 200, gameMap.at(29,5 ));
        world.addPlayer(player, gameMap.at(29, 5));



        gameMap.at(28, 0).setGround(new Gate(burialGroundMap.at(20,3), "The Burial Ground"));
        burialGroundMap.at(19,3).setGround(new Gate(gameMap.at(29,5), "The Abandoned Village"));
        burialGroundMap.at(21,3).setGround(new Gate(ancientWoodsMap.at(29,5), "The Ancient Woods"));
        ancientWoodsMap.at(28,5).setGround(new Gate(burialGroundMap.at(20,3), "The Burial Ground"));
        ancientWoodsMap.at(30,5).setGround(new Gate(abxervyerMap.at(2,8), "Abxervyer, The Forest Watcher's Battle Room"));
        overgrownSanctuaryMap.at(0,4).setGround(new Gate(abxervyerMap.at(2,8), "Abxervyer, The Forest Watcher's Battle Room"));


        HashMap<Location, String> abxervyerTravelLocations = new HashMap<>();
        abxervyerTravelLocations.put(ancientWoodsMap.at(29,5), "The Ancient Woods");
        abxervyerTravelLocations.put(overgrownSanctuaryMap.at(1,4), "The Overgrown Sanctuary");

        AbxervyerForestWatcher abxervyerForestWatcher = new AbxervyerForestWatcher(ancientWoodsMap, abxervyerTravelLocations);
        abxervyerMap.at(14,8).addActor(abxervyerForestWatcher);


        ancientWoodsMap.at(21,3).addActor(new IsolatedTraveller());

        gameMap.at(35,11).addActor(new WanderingUndead(gameMap));


        ancientWoodsMap.at(30, 0).setGround(new Gate(abxervyerMap.at(0,0), "Abxervyer, The Forest Watcher's Battle Room"));


        gameMap.at(43,10).addActor(new Blacksmith());
        gameMap.at(27,6).addItem(new BroadSword());
        gameMap.at(30,11).setGround(new Graveyard(new WanderingUndead(gameMap)));
        gameMap.at(50,1).setGround(new Graveyard(new WanderingUndead(gameMap)));

        burialGroundMap.at(2,14).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(6,0).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(23,2).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(38,12).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(36,1).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));


        ancientWoodsMap.at(12,11).addItem(new Bloodberry());
        ancientWoodsMap.at(14,11).setGround(new Bush(new RedWolf(ancientWoodsMap)));
        ancientWoodsMap.at(11,9).setGround(new Bush(new RedWolf(ancientWoodsMap)));
        ancientWoodsMap.at(27,0).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));
        ancientWoodsMap.at(20,1).addItem(new Bloodberry());
        ancientWoodsMap.at(22,6).addItem(new Bloodberry());
        ancientWoodsMap.at(35,11).addItem(new Bloodberry());
        ancientWoodsMap.at(34,10).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));
        ancientWoodsMap.at(46,5).setGround(new Bush(new RedWolf(ancientWoodsMap)));
        ancientWoodsMap.at(56,8).addItem(new Bloodberry());
        ancientWoodsMap.at(57,3).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));



        abxervyerMap.at(27, 8).setGround(new Hut(new ForestKeeper(abxervyerMap)));
        abxervyerMap.at(35, 3).setGround(new Hut(new ForestKeeper(abxervyerMap)));
        abxervyerMap.at(18, 7).setGround(new Hut(new ForestKeeper(abxervyerMap)));

        abxervyerMap.at(29, 10).setGround(new Bush(new RedWolf(abxervyerMap)));
        abxervyerMap.at(37, 5).setGround(new Bush(new RedWolf(abxervyerMap)));
        abxervyerMap.at(20, 9).setGround(new Bush(new RedWolf(abxervyerMap)));

        overgrownSanctuaryMap.at(27, 8).setGround(new Hut(new EldentreeGuardian(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(35, 3).setGround(new Hut(new EldentreeGuardian(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(18, 7).setGround(new Hut(new EldentreeGuardian(overgrownSanctuaryMap)));

        overgrownSanctuaryMap.at(29, 10).setGround(new Bush(new LivingBranch(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(37, 5).setGround(new Bush(new LivingBranch(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(20, 9).setGround(new Bush(new LivingBranch(overgrownSanctuaryMap)));

        overgrownSanctuaryMap.at(25, 2).setGround(new Graveyard(new HollowSoldier(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(33, 4).setGround(new Graveyard(new HollowSoldier(overgrownSanctuaryMap)));
        overgrownSanctuaryMap.at(16, 6).setGround(new Graveyard(new HollowSoldier(overgrownSanctuaryMap)));


        GiantHammer giantHammer = new GiantHammer();
        abxervyerMap.at(29, 2).addItem(giantHammer);



        world.run();
    }
}
