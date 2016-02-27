package view.configs;
/**
 *Entities supported directions.
 */
public enum Directions {
    /**
     * Left direction.
     */
    LEFT("sx"),
    /**
     * Right direction.
     */
    RIGHT("dx"),
    /**
     * No direction.
     */
    NONE("none");
    
    private final String name;
    
    Directions(final String name) {
        this.name = name;
    }
    /**
     * Returns the string associated with every element.
     * @return The string associated with this element.
     */
    public String getName() {
        return this.name;
    }
}
