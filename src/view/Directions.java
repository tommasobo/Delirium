package view;

public enum Directions {
    LEFT("sx"),
    RIGHT("dx"),
    NONE("none");
    
    private final String name;
    
    private Directions(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
