package view;

public enum Entities {

    JOYHERO("joyhero");
    
    private final String name;

    private Entities(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
