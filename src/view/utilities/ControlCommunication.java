package view.utilities;

import view.configs.Actions;
import view.configs.Entities;

/**
 * A communication object used by controller to give to view informations about
 * entities to represent.
 */
public class ControlCommunication {
    private final int code;
    private final int life;
    private final Entities entity;
    private final ViewPhysicalProperties property;
    private final Actions action;

    /**
     * ControlCommunication Constructor.
     * 
     * @param code
     *            Entity's ID
     * @param entity
     *            The entity to represent
     * @param life
     *            Entity's life
     * @param property
     *            Entity's position, speed and direction
     * @param action
     *            Entity's current action
     */
    public ControlCommunication(final int code, final Entities entity, final int life,
            final ViewPhysicalProperties property, final Actions action) {
        this.code = code;
        this.entity = entity;
        this.life = life;
        this.property = property;
        this.action = action;
    }

    /**
     * This method returns the entity's ID.
     * 
     * @return Entity's ID
     */
    public int getCode() {
        return this.code;
    }

    /**
     * This method returns the entity.
     * 
     * @return The entity to represent.
     */
    public Entities getEntity() {
        return this.entity;
    }

    /**
     * This method returns the physical properties.
     * 
     * @return The physical properties
     */
    public ViewPhysicalProperties getProperty() {
        return this.property;
    }

    /**
     * This method returns the entity current action.
     * 
     * @return The current action
     */
    public Actions getAction() {
        return this.action;
    }

    /**
     * This method returns entity's life.
     * 
     * @return Entity's life
     */
    public int getLife() {
        return this.life;
    }

}
