package view.configs;

import javafx.animation.Timeline;

/**
 * Entities actions supported by this game.
 */
public enum Actions {
    /**
     * To jump.
     */
    JUMP("jump", Timeline.INDEFINITE),
    /**
     * To fall.
     */
    FALL("fall", Timeline.INDEFINITE),
    /**
     * To move.
     */
    MOVE("move", Timeline.INDEFINITE),
    /**
     * To stay still.
     */
    IDLE("idle", Timeline.INDEFINITE),
    /**
     * To shoot a bullet.
     */
    SHOOT("shoot", 1),
    /**
     * To die.
     */
    DEATH("death", 1);

    private final String string;
    private final int duration;

    /**
     * Actions constructor
     * 
     * @param string
     *            The string associated to an element
     * @param duration
     *            The default duration of an action
     */
    Actions(final String string, final int duration) {
        this.string = string;
        this.duration = duration;
    }

    /**
     * @return The string associated to an element
     */
    public String getString() {
        return this.string;
    }

    /**
     * @return The default duration of an action
     */
    public int getDuration() {
        return this.duration;
    }
}
