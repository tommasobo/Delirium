package view.configs;

public enum Notifications {
    
    PAUSE("PAUSE"),
    PLAY(""),
    WIN("YOU WON"),
    LOSE("GAME OVER");
    
    private final String toShow;
    
    Notifications(final String toShow) {
        this.toShow = toShow;
    }
    
    public String getToShow() {
        return this.toShow;
    }

}
