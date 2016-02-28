package model.arena.manager;

import java.util.Set;

import model.arena.entities.Entities;

interface ActiveMovementDatabase {

    /**
     * Set an entity as dependent to other
     * @param masterEntityCode the "under entity"
     * @param passiveEntity the "up" entity
     */
    void putEntity(int masterEntityCode, Entities passiveEntity);

    /**
     * 
     * @param masterEntityCode Code of entity
     * @return the set contains the entities that are on the input entity
     */
    Set<Entities> getRelativeEntities(int masterEntityCode);

    /**
     * 
     * @param masterEntityCode The entity
     * @return the set contains the entities that are on the input entity
     */
    void removeEntityFromAllDependences(Entities entity);

}