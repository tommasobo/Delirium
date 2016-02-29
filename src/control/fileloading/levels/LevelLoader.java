package control.fileloading.levels;

import java.util.List;

import control.viewcomunication.translation.EntitiesDatabase;
import model.transfertentities.EntitiesInfo;
import utility.Pair;

/**
 * Interface that declares methods for a working level loader.
 * 
 * @author Matteo Magnani
 *
 */
public interface LevelLoader {

    /**
     * 
     * @return the level structure
     */
    Pair<List<EntitiesInfo>, EntitiesDatabase> getLevelStructure();
}