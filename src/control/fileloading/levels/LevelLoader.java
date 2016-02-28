package control.fileloading.levels;

import java.util.List;

import control.viewcomunication.translation.EntitiesDatabase;
import model.transfertentities.EntitiesInfo;
import utility.Pair;

/**
 * Interface that declare methods for a working level loader
 * @author Matteo Magnani
 *
 */
public interface LevelLoader {

    public Pair<List<EntitiesInfo>, EntitiesDatabase> getLevelStructure();
}