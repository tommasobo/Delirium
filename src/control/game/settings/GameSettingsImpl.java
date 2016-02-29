package control.game.settings;

import java.util.Iterator;
import java.util.List;

import control.fileloading.levels.Levels;

/**
 * Implementation of interface GameSettings, contains all possible settings of
 * the game.
 * 
 * @author Matteo Magnani
 *
 */
public class GameSettingsImpl implements GameSettings {
    private final List<Levels> levels;
    private GameDifficulty gameDifficulty;

    /**
     * 
     * @param levels
     *            The ordered list of levels that player have to complete
     * @param gameDifficulty
     *            The initial game difficulty
     */
    public GameSettingsImpl(final List<Levels> levels, final GameDifficulty gameDifficulty) {
        this.levels = levels;
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public Iterator<Levels> getLevelIterator() {
        return this.levels.iterator();
    }

    @Override
    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    @Override
    public void setGameDifficulty(final GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public EntityStatsModifier getEntityStatsModifier() {
        return new EntityStatsModifierImpl(this.gameDifficulty);
    }

}
