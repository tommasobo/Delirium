package view.configs;

/**
 * Dynamic scene's special states.
 */
public enum Notifications {
    /**
     * Pauses game.
     */
    PAUSE("PAUSE"),
    /**
     * Plays game.
     */
    PLAY(""),
    /**
     * User has won.
     */
    WIN("YOU WON"),
    /**
     * User has lost.
     */
    LOSE("GAME OVER");

    private final String toShow;

    /**
     * Notifications constructor
     * 
     * @param toShow
     *            The string associated with an element
     */
    Notifications(final String toShow) {
        this.toShow = toShow;
    }

    /**
     * @return The string associated with an element
     */
    public String getToShow() {
        return this.toShow;
    }

}
