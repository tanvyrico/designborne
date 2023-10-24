package game;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
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
        IsolatedTraveller isolatedTraveller = new IsolatedTraveller();
        gameMap.at(30, 5).addActor(isolatedTraveller);
        gameMap.at(30,8).addActor(new LivingBranch(gameMap));
        Blacksmith blacksmith = new Blacksmith();
        gameMap.at(28,5).addActor(blacksmith);

        gameMap.at(28,5).addItem(new Runes(1000));
        gameMap.at(27,5).addItem(new Runes(5000));
        gameMap.at(28,6).addItem(new Runes(8000));

        gameMap.at(31,5).setGround(new Void());



        gameMap.at(30,8).addItem(new HealingVial());


        gameMap.at(28, 6).setGround(new Gate(burialGroundMap.at(29,7), "The Burial Ground"));
        burialGroundMap.at(31, 5).setGround(new Gate(gameMap.at(29,7), "The Ancient Woods"));
        ancientWoodsMap.at(30, 0).setGround(new Gate(abxervyerMap.at(0,0), "Abxervyer, The Forest Watcher's Battle Room"));

//        gameMap.at(28, 6).setGround(new Gate(burialGroundMap.at(29,7), "The Burial Ground"));
//        burialGroundMap.at(31, 5).setGround(new Gate(gameMap.at(29,7), "The Ancient Woods"));
//        ancientWoodsMap.at(30, 0).setGround(new Gate(abxervyerMap.at(0,0), "Abxervyer, The Forest Watcher's Battle Room"));

        BroadSword broadSword = new BroadSword();
        gameMap.at(29,6).addItem(broadSword);

        ////
        gameMap.at(29,6).addItem(new OldKey());
        ////


        gameMap.at(27, 8).setGround(new Graveyard(new WanderingUndead(gameMap)));
        gameMap.at(35, 3).setGround(new Graveyard(new WanderingUndead(gameMap)));
        gameMap.at(18, 7).setGround(new Graveyard(new WanderingUndead(gameMap)));

        burialGroundMap.at(27, 8).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(35, 3).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));
        burialGroundMap.at(18, 7).setGround(new Graveyard(new HollowSoldier(burialGroundMap)));

        ancientWoodsMap.at(27, 8).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));
        ancientWoodsMap.at(35, 3).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));
        ancientWoodsMap.at(18, 7).setGround(new Hut(new ForestKeeper(ancientWoodsMap)));

        ancientWoodsMap.at(29, 10).setGround(new Bush(new RedWolf(ancientWoodsMap)));
        ancientWoodsMap.at(37, 5).setGround(new Bush(new RedWolf(ancientWoodsMap)));
        ancientWoodsMap.at(20, 9).setGround(new Bush(new RedWolf(ancientWoodsMap)));

        ancientWoodsMap.at(21, 10).addItem(new Bloodberry());
        ancientWoodsMap.at(20, 5).addItem(new Bloodberry());
        ancientWoodsMap.at(10,9).addItem(new Bloodberry());

        abxervyerMap.at(27, 8).setGround(new Hut(new ForestKeeper(abxervyerMap)));
        abxervyerMap.at(35, 3).setGround(new Hut(new ForestKeeper(abxervyerMap)));
        abxervyerMap.at(18, 7).setGround(new Hut(new ForestKeeper(abxervyerMap)));

        abxervyerMap.at(29, 10).setGround(new Bush(new RedWolf(abxervyerMap)));
        abxervyerMap.at(37, 5).setGround(new Bush(new RedWolf(abxervyerMap)));
        abxervyerMap.at(20, 9).setGround(new Bush(new RedWolf(abxervyerMap)));

        overgrownSanctuaryMap.at(18, 7).setGround(new Hut(new EldentreeGuardian(overgrownSanctuaryMap)));


        GiantHammer giantHammer = new GiantHammer();
        abxervyerMap.at(29, 2).addItem(giantHammer);

        AbxervyerForestWatcher abxervyerForestWatcher = new AbxervyerForestWatcher(ancientWoodsMap, ancientWoodsMap.at(0,0), overgrownSanctuaryMap.at(5,1));
        abxervyerMap.at(14,8).addActor(abxervyerForestWatcher);

        world.run();
    }
}
