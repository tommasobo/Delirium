package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.utility.Actions;

/**
 * This interface explain the behavior of the entity.
 * It is the communication structure from model to control.
 * 
 * @author josephgiovanelli
 *
 */
public interface EntitiesInfoToControl {

    /**
     * Get the code of entity.
     * @return : the value that you want.
     */
    int getCode();

    /**
     * Get the life of the entity.
     * @return : the value that you want.
     */
    int getLife();

    /**
     * Get the position of the entity.
     * @return : the value that you want.
     */
    Position getPosition();

    /**
     * Get the action of the entity.
     * @return : the value that you want.
     */
    Actions getAction();

    /**
     * Get the speed of the entity.
     * This is sent to the control because with the power up might be useful.
     * @return : the value that you want.
     */
    Optional<Integer> getSpeed();

}
