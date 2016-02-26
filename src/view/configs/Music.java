package view.configs;

public enum Music {
    
    MENUTHEME("menu"),
    GAMETHEME("game");
    
    private final String name;
    
    Music(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
