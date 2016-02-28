package view.configs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Drawable entities with their configuration parameters.
 */
public enum Entities {
    /**
     * Main character Magno.
     */
    MAGNO("magno", true, 4, Actions.values()),
    /**
     * Main character Bocc.
     */
    BOCC("bocc", true, 4, Actions.values()),
    /**
     * Main character Joy.
     */
    JOY("joy", true, 4, Actions.values()),
    /**
     * Magno's bullet.
     */
    MAGNOBULLET("magnobullet", true, 3, Actions.MOVE, Actions.DEATH),
    /**
     * Bocc's bullet.
     */
    BOCCBULLET("boccbullet", true, 3, Actions.MOVE, Actions.DEATH),
    /**
     * Joy's bullet.
     */
    JOYBULLET("joybullet", true, 3, Actions.MOVE, Actions.DEATH),
    /**
     * Dinosaur enemy.
     */
    DINO("dino", true, 4, Actions.IDLE, Actions.SHOOT, Actions.DEATH),
    /**
     * Fox enemy.
     */
    VOLPE("volpe", true, 3, Actions.values()),
    /**
     * Bug enemy.
     */
    BUG("bug", true, 3, Actions.MOVE, Actions.IDLE, Actions.SHOOT, Actions.DEATH, Actions.FALL),
    /**
     * Generic bullet.
     */
    BULLET("bullet", true, 3, Actions.MOVE, Actions.DEATH),
    /**
     * World ground.
     */
    GROUND("ground", false, 3, Actions.IDLE),
    /**
     * World platform.
     */
    PLATFORM("platform", false, 3, Actions.MOVE, Actions.IDLE, Actions.JUMP, Actions.FALL),
    /**
     * Main goal of the level.
     */
    GOAL("goal", true, 3, Actions.IDLE),
    /**
     * World traps.
     */
    TRAP("trap", false, 3, Actions.IDLE);
    
    private final String name;
    private final boolean animated;
    private final int nAssets;
    private final Actions[] allowedActions;
    
    /**
     * Entities constructor.
     * 
     * @param name
     *            The string associated to an element
     * @param animated
     *            Defines if the entity is animated or not
     * @param nAssets
     *            The number of images available for every allowed action
     * @param actions
     *            Entity's allowed actions
     */
    Entities(final String name, final boolean animated, final int nAssets, final Actions...actions) {
        this.name = name;
        this.animated = animated;
        this.nAssets = nAssets;
        this.allowedActions = actions;
    }
    /**
     * @return The string associated to an element of this enum
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return If the entity is animated or static
     */
    public boolean isAnimated() {
        return this.animated;
    }
    /**
     * @return The number of images available for every allowed action
     */
    public int getnAssets() {
        return this.nAssets;
    }
    /**
     * @return An unmodifiable list of allowed actions
     */
    public List<Actions> getAllowedActions() {
        return Collections.unmodifiableList(Arrays.asList(this.allowedActions));
    }
    
}
