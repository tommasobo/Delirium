package control.game.settings;

import java.util.Iterator;

import control.fileloading.levels.Levels;

/**
 * Interface that declares methods for a full implementation of game's settings.
 * 
 * @author Matteo Magnani
 *
 */
public interface GameSettings {

    /**
     * 
     * @return Iterator that represents the order of game levels
     */
    Iterator<Levels> getLevelIterator();

    /**
     * 
     * @return The game difficulty
     */
    GameDifficulty getGameDifficulty();

    /**
     * 
     * @param gameDifficulty
     *            The game difficulty
     */
    void setGameDifficulty(GameDifficulty gameDifficulty);

    /**
     * 
     * @return The entities' statistics modifier according to actual game
     *         difficulty
     */
    EntityStatsModifier getEntityStatsModifier();

}