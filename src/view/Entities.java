package view;

public enum Entities {

    JOYHERO("joyhero"),
    MONSTER1("monster1");
    
    private final String name;

    private Entities(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
