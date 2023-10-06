package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Class representing a wall ground on the game map that blocks actor movement.
 * Created by: Lim Hung Xuan
 * @author Riordan D. Alfredo
 * Modified by: Group6
 */
public class Wall extends Ground {

    /**
     * Constructor for the Wall class.
     */
    public Wall() {
        super('#');
    }

    /**
     * Checks whether an actor can enter the wall, which is always false since walls are impassable.
     *
     * @param actor The actor attempting to enter the wall.
     * @return Always returns false, indicating that actors cannot enter a wall.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
