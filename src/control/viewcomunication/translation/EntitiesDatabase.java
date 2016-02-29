package control.viewcomunication.translation;

import java.util.List;

import model.transfertentities.EntitiesInfo;
import utility.Dimension;
import view.configs.Entities;

/**
 * This interface declare methods to manage a database that associate to each
 * game entity to view representation.
 * 
 * @author Matteo Magnani
 *
 */
public interface EntitiesDatabase {

    /**
     * Put the input entity's code in the database with the relative view
     * representation.
     * 
     * @param code
     *            The code of the entity
     * @param entity
     *            The entity's view representation
     */
    void putEntity(Integer code, Entities entity);

    /**
     * Put the input entity in the database with the relative view
     * representation.
     * 
     * @param modelEnt
     *            The entity to put in database
     * @param entity
     *            The entity's view representation
     */
    void putEntity(final EntitiesInfo modelEnt, final Entities entity);

    /**
     * Put the input entity in the database with the relative view
     * representation and returns a copy of the entity with the appropriate
     * univocal code.
     * 
     * @param modelEnt
     *            The entity to put in database
     * @param entity
     *            The entity's view representation
     * @return A copy of the entity with the appropriately code appropriately
     */
    EntitiesInfo putEntityAndSetCode(EntitiesInfo modelEnt, Entities entity);

    /**
     * Put the input bullets in the database with the relative view
     * representation according to code of the entity that have fired it.Returns
     * a copy of the entity with the appropriate univocal code.
     * 
     * @param bullets
     *            The list of bullets to put in database
     * @return A copy of the entity with the appropriately code appropriately
     */
    List<EntitiesInfo> putBulletsAndSetCodes(List<EntitiesInfo> bullets);

    /**
     * Get the view representation of the input entity code.
     * 
     * @param code
     *            The code of the entity
     * @return The view representation
     */
    Entities getViewEntity(Integer code);

    /**
     * 
     * @return The dimension of the level's arena
     */
    Dimension getArenaDimension();

    /**
     * Put the input entities in the database with the relative view
     * representation.
     * 
     * @param entities
     *            The list of entities to put in the database
     * @param entity
     *            The view representation of the entities to put
     */
    void putEntities(final List<EntitiesInfo> entities, final Entities entity);

    /**
     * Put the input entities in the database with the relative view
     * representation. Returns a copy of the entity with the appropriate
     * univocal code.
     * 
     * @param entities
     *            The list of entities to put in the database
     * @param entity
     *            The view representation of the entities to put
     * @return A copy of the entity with the appropriate univocal code
     */
    List<EntitiesInfo> putEntitiesAndSetCodes(List<EntitiesInfo> entities, Entities entity);

}
