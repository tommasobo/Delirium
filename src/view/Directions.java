package view;

public enum Directions {
    LEFT("left"),
    RIGHT("right"),
    NONE("none");
    
    private final String name;
    
    private Directions(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
