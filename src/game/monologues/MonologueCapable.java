package game.monologues;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing the ability to generate a monologue for a specific player (Actor).
 * @author Enrico Tanvy
 */
public interface MonologueCapable {

    /**
     * Generates a monologue for the provided player (Actor).
     *
     * @param player the player (Actor) for whom the monologue is generated
     * @return a string representing the generated monologue
     */
    String generateMonologue(Actor player);
}
