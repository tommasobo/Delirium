package control;

public enum MenuCategory {
    DEFAULT("Default"),
    DIFFICULTY("Difficuly");
    private final String name;
    private MenuCategory(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
}
