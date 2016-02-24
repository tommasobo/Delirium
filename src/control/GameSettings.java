package control;

import java.util.Iterator;
import java.util.List;

public interface GameSettings {

    List<Levels> getLevels();

    Iterator<Levels> getLevelIterator();

    GameDifficulty getGameDifficulty();

    void setGameDifficulty(GameDifficulty gameDifficulty);

    EntityStatsModifier getEntityStatsModifier();

}