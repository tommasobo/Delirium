package model.arena.utility;

/**
 * Specified the types of the movement's entities.
 * Is used in @MovementMangerFactory in order to filter the @MovementManager.
 * @author josephgiovanelli
 *
 */
public enum MovementTypes {
    /**
     * The entity isn't proactive.
     */
    REACTIVE,
    /**
     * The entity's movement is horizontal linear.
     */
    HORIZONTAL_LINEAR,
    /**
     * The entity's movement is vertical linear.
     */
    VERTICAL_LINEAR, 
    /**
     * The entity's movement is pseudo-random.
     */
    RANDOM,
    /**
     * Hero has own movement.
     */
    HERO;

}
