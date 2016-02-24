package control;

import java.util.Iterator;
import java.util.List;

public class GameSettings {
    private final List<Levels> levels;
    
    public GameSettings(List<Levels> levels) {
        this.levels = levels;
    }

    public List<Levels> getLevels() {
        return levels;
    }
    
    public Iterator<Levels> getLevelIterator() {
        return this.getLevels().iterator();
    }
}
