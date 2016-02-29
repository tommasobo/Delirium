package model.arena.entities.movement;

import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;

/**
 * This is an application of the Strategy Pattern. The implementation of this
 * interface determinate the pattern of movement of the entity.
 * 
 * @author josephgiovanelli
 *
 */
public interface MovementManager {

    /**
     * Get the defensive copy of the actual position.
     * 
     * @return : the position that you ask.
     */
    Position getPosition();

    /**
     * Set the value of the position.
     * 
     * @param point
     *            : the point of the position.
     * @param direction
     *            : the direction of the position.
     */
    void setPosition(final Point point, final Directions direction);

    /**
     * Get the speed of the entity.
     * 
     * In the possibility of the powerUp.
     * 
     * @return : the speed that you want.
     */
    int getSpeed();

    /**
     * Set the value of the speed.
     * In the possibility of the powerUp.
     * 
     * @param speed
     *            : the value that you want to set.
     */
    void setSpeed(final int speed);

    /**
     * Get the limits of the entity.
     * 
     * @return : the value that you want to know.
     */
    Bounds getBounds();

    /**
     * Get if the entity can fly or is subject to gravity.
     * @return : if can fly.
     */
    boolean isCanFly();

    /**
     * The override of this method is pattern of the movement.
     * Each implementation is unique and do different things.
     * @return : the new position based on the pattern.
     */
    Position getNextMove();

    /**
     * Set the action that you want.
     * @param action : the value that you want to set.
     */
    void setAction(final Actions action);

    /**
     * Get the Action of the entity.
     * @return : the value of the action that you want to know.
     */
    Actions getAction();
}
