package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;

/**
 * This interface explain the behavior of the entity.
 * It is the communication structure from control to model.
 * 
 * @author josephgiovanelli
 *
 */
public interface EntitiesInfo {

    /**
     * Get the code of entity.
     * @return : the value that you want.
     */
    int getCode();

    /**
     * Get the information of the the movement of the entity.
     * @return : the value that you want.
     */
    Optional<MovementInfo> getMovementInfo();

    /**
     * Get the position of the entity.
     * @return : the value that you want.
     */
    Position getPosition();

    /**
     * Get the life of the entity.
     * @return : the value that you want.
     */
    int getLife();

    /**
     * Get the lifePattern of the entity.
     * @return : the value that you want.
     */
    LifePattern getLifePattern();

    /**
     * Get the information of the shoot of the entity.
     * @return : the value that you want.
     */
    Optional<ShootInfo> getShootInfo();

    
    /**
     * Get the contact damage of the entities.
     * @return : the value that you want.
     */
    Optional<Integer> getContactDamage();

}