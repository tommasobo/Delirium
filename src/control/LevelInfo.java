package control;

import java.util.List;

import control.EntitiesInfoStore;
import utility.Dimension;

/**
 * Interface that declare all methods of a working object that contains level
 * informations
 * 
 * @author Matteo Magnani
 *
 */
public interface LevelInfo {

    /**
     * 
     * @return The list of level's entities
     */
    List<EntitiesInfoStore> getEntities();

    /**
     * 
     * @return The level dimension
     */
    Dimension getLevelDimension();

}