package model;

import java.util.List;

import model.arena.utility.Actions;
import model.arena.utility.Directions;
import model.exception.IllegalMonsterBoundsException;
import model.exception.NotUniqueCodeException;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoToControl;

/**
 * This Interface is the communication entity that the control can use in order
 * to talk with the model.
 * 
 * @author josephgiovanelli
 *
 */
public interface Model {

    /**
     * This method notify to model when he has to change the direction of the
     * hero.
     * 
     * @param direction
     *            : the new direction of the hero.
     */
    void notifyEvent(final Directions direction);

    /**
     * This method notify to model the new action of the hero.
     * 
     * @param action
     *            : the new one of the hero.
     */
    void notifyEvent(final Actions action);

    /**
     * The control can call this method in order to send the new quantum of
     * time. The Arena is updated by moving the monster, modify the life of them
     * and a component checks the collision.
     * 
     * @return : with a communication structure this method communicate to the
     *         control the bullet of some monster that has shoots in order to he
     *         can instance them in the method @putBullet
     */
    List<EntitiesInfo> updateArena();

    /**
     * With this method the control can get informations of any entities and use
     * them like do he wants because are defensive copies.
     * 
     * @return : the informations are sent with another structure.
     */
    List<EntitiesInfoToControl> getState();

    /**
     * This method instances the arena, that means any entities like hero, goal
     * and any types of monster, platform ecc.. In this method are used the
     * Builder pattern, the Factory Pattern and the Visitor Pattern.
     * 
     * @param entitiesInfo
     *            : is the object within the communication happen.
     * @throws NotUniqueCodeException
     *             : throw this exception if there is a duplication of codes.
     * @throws IllegalMonsterBoundsException
     *             : throw this exception if the monster is instantiated in a
     *             location not consistent with his limits.
     */
    void createArena(final List<EntitiesInfo> entitiesInfo)
            throws NotUniqueCodeException, IllegalMonsterBoundsException;

    /**
     * If the @updateArena notify the control that there are some bullets to
     * instance then this method can put them into the arena.
     * 
     * @param entitiesInfo
     *            : is the object within the communication happen.
     * @throws NotUniqueCodeException
     *             : throw this exception if there is a duplication of codes.
     * @throws IllegalMonsterBoundsException
     *             : throw this exception if the monster is instantiated in a
     *             location not consistent with his limits.
     */
    void putBullet(final List<EntitiesInfo> entitiesInfo) throws NotUniqueCodeException, IllegalMonsterBoundsException;

}
