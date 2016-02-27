package control;

import java.util.Iterator;

/**
 * Interface that declare methods for a full implementation of game settings
 * 
 * @author Matteo Magnani
 *
 */
public interface GameSettings {

    /**
     * 
     * @return The iterator that represents the order of game levels
     */
    Iterator<Levels> getLevelIterator();

    /**
     * 
     * @return The game difficulty
     */
    GameDifficulty getGameDifficulty();

    /**
     * 
     * @param gameDifficulty The game difficulty
     */
    void setGameDifficulty(GameDifficulty gameDifficulty);

    /**
     * 
     * @return The entities' statistics modifier according to actual game difficulty
     */
    EntityStatsModifier getEntityStatsModifier();

}