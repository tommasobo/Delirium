package control;

import java.util.Iterator;
import java.util.List;

public class GameSettingsImpl implements GameSettings{
    private final List<Levels> levels;
    private GameDifficulty gameDifficulty;
    
    public GameSettingsImpl(List<Levels> levels, GameDifficulty gameDifficulty) {
        this.levels = levels;
        this.gameDifficulty = gameDifficulty;
    }

    public List<Levels> getLevels() {
        return levels;
    }
    
    public Iterator<Levels> getLevelIterator() {
        return this.getLevels().iterator();
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
    
    public EntityStatsModifier getEntityStatsModifier() {
        return new EntityStatsModifierImpl(this.gameDifficulty);
    }
    
    
}
