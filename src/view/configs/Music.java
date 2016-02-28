package view.configs;

/**
 * Reproducible audio tracks.
 */
public enum Music {
    /**
     * The default track for menus.
     */
    MENUTHEME("menu"),
    /**
     * The default track inside the game.
     */
    GAMETHEME("game");

    private final String name;

    /**
     * Music constructor
     * 
     * @param name
     *            The string associated with an element
     */
    Music(final String name) {
        this.name = name;
    }

    /**
     * @return The string associated with an element
     */
    public String getName() {
        return this.name;
    }
}
