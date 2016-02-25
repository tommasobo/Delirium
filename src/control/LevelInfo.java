package control;

import java.util.List;

import utility.Dimension;

public interface LevelInfo {

    List<EntitiesInfoStore> getEntities();

    Dimension getLevelDimension();

}