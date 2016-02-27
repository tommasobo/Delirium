package view.utilities;

import view.configs.Actions;
import view.configs.Entities;

/**
 * A communication object used by controller to give to view informations about
 * entities to represent.
 */
public class ControlComunication {
    private final int code;
    private final int life;
    private final Entities entity;
    private final ViewPhysicalProperties property;
    private final Actions action;

    /**
     * ControlCommunication Constructor.
     * 
     * @param code
     *            The entity ID
     * @param entity
     *            The entity to represent
     * @param life
     *            Entity life
     * @param property
     *            Entity position, speed and direction
     * @param action
     *            Entity current action
     */
    public ControlComunication(final int code, final Entities entity, final int life,
            final ViewPhysicalProperties property, final Actions action) {
        this.code = code;
        this.entity = entity;
        this.life = life;
        this.property = property;
        this.action = action;
    }

    /**
     * Get the entity ID.
     * 
     * @return The entity ID
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Get the entity.
     * 
     * @return The entity to represent.
     */
    public Entities getEntity() {
        return this.entity;
    }

    /**
     * Get the physical properties.
     * 
     * @return The physical properties
     */
    public ViewPhysicalProperties getProperty() {
        return this.property;
    }

    /**
     * Get the entity current action.
     * 
     * @return The current action
     */
    public Actions getAction() {
        return this.action;
    }

    /**
     * Get entity life.
     * 
     * @return Entity life
     */
    public int getLife() {
        return this.life;
    }

}
