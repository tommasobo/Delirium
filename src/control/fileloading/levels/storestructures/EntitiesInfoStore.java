package control.fileloading.levels.storestructures;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;
import model.transfertentities.EntitiesInfo;

/**
 * That interface extends EntitiesInfo(the interface for the communication
 * structure to model) to add setters, very helpful for elaboration of entities
 * during the file loading
 * 
 * @author Matteo Magnani
 *
 */
public interface EntitiesInfoStore extends EntitiesInfo{

    /**
     * 
     * @param code Entity's code
     */
    void setCode(int code);

    /**
     * 
     * @return The movement informations of the entity, empty optional if entity cannot move. Isn't a protected copy
     */
    Optional<MovementInfoStore> getMovementInfoStore();

    /**
     * 
     * @return The shoot informations of the entity, empty optional if entity cannot shoot. Isn't a protected copy
     */
    Optional<ShootInfoStore> getShootInfoStore();

    /**
     * 
     * @param position Entity's position
     */
    void setPosition(Position position);

    /**
     * 
     * @param movementInfo Entity's movements informations, empty optional if entity cannot move
     */
    void setMovementInfo(Optional<MovementInfoStore> movementInfo);
    
    /**
     * 
     * @param life Entity's life amount
     */
    void setLife(int life);

    /**
     * 
     * @param lifePattern Entity's life pattern
     */
    void setLifePattern(LifePattern lifePattern);

    /**
     * 
     * @param shootInfo Entity's shoot informations, empty optional if entity cannot shoot
     */
    void setShootInfo(Optional<ShootInfoStore> shootInfo);

    /**
     * 
     * @param contactDamage Entity's contact damage, empty optional if the entity cannot inflict contact damage
     */
    void setContactDamage(Optional<Integer> contactDamage);

    /**
     * 
     * @param entityType Entity's view representation
     */
    void setEntityType(view.configs.Entities entityType);
    
    /**
     * 
     * @return Entity's view representation
     */
    public view.configs.Entities getEntityType();

}