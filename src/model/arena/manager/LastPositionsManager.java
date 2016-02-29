package model.arena.manager;

import model.arena.entities.Entities;
import model.arena.entities.Position;

/**
 * 
 * @author Matteo Magnani
 *
 */
interface LastPositionsManager {

    /**
     * 
     * @param entity
     * @return the last position of the entity
     */
    Position getLastPosition(Entities entity);

    /**
     * 
     * @param code
     *            Entity's code
     * @return the last position of the entity
     */
    Position getLastPosition(int code);

    /**
     * 
     * @param entity
     *            The entity
     * @param position
     *            The new position
     * @return The difference between last position and the new position
     */
    PointOffset getOffsetFromLastPosition(Entities entity, Position position);

    /**
     * 
     * @param entity
     *            Entity's code
     * @param position
     *            The new position
     * @return The difference between last position and the new position
     */
    PointOffset getOffsetFromLastPosition(int code, Position position);

    /**
     * Put in database the entity with its actual position
     * 
     * @param entity
     *            the entity
     */
    void putPosition(Entities entity);

    /**
     * Put in database the entity with the input position
     * 
     * @param entity
     *            The entity
     * @param position
     *            Position of the entity
     */
    void putPosition(Entities entity, Position position);

    /**
     * Put in database the entity with the input position
     * 
     * @param entity
     *            Entity's code
     * @param position
     *            Position of the entity
     */
    void putPosition(int code, Position position);

}