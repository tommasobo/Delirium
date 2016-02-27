package model.transfertentities;

import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.MovementTypes;

/**
 * This class provide the information of the movement of the entity.
 * @author josephgiovanelli
 *
 */
public interface MovementInfo {

    
    /**
     * Get the speed of the entity.
     * 
     * @return : the speed that you want.
     */
    int getSpeed();

    /**
     * Get the limits of the entity.
     * 
     * @return : the value that you want to know.
     */
    Bounds getBounds();

    /**
     * Get the Action of the entity.
     * @return : the value of the action that you want to know.
     */
    Actions getActions();

    /**
     * Get if the entity can fly or is subject to gravity.
     * @return : if can fly.
     */
    boolean isCanFly();

    /**
     * Get the movementTypes of the monster.
     * @return : the value that you want.
     */
    MovementTypes getMovementTypes();

}