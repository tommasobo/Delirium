package control.viewcomunication;

/**
 * Enumeration that contains all possible menu categories with their names
 * 
 * @author Matteo Magnani
 *
 */
public enum MenuCategory {
    DEFAULT("DEFAULT"), 
    DIFFICULTY("DIFFICULTY");
    private final String name;

    MenuCategory(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return Difficulty's name
     */
    public String getName() {
        return name;
    }

}
