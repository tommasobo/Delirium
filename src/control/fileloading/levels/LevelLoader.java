package control.fileloading.levels;

import java.util.List;

import control.viewcomunication.translation.EntitiesDatabase;
import model.transfertentities.EntitiesInfo;

/**
 * Interface that declare methods for a working level loader
 * @author Matteo Magnani
 *
 */
public interface LevelLoader {

    List<EntitiesInfo> getEntities();

    EntitiesDatabase getDatabase();

}