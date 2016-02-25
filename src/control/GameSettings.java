package control;

import java.util.Iterator;

public interface GameSettings {

    Iterator<Levels> getLevelIterator();

    GameDifficulty getGameDifficulty();

    void setGameDifficulty(GameDifficulty gameDifficulty);

    EntityStatsModifier getEntityStatsModifier();

}