package view;

public enum Notifications {
    
    PAUSE("PAUSE"),
    PLAY(""),
    WIN("YOU WON"),
    LOSE("GAME OVER");
    
    private final String toShow;
    
    private Notifications(final String toShow) {
        this.toShow = toShow;
    }
    
    String getToShow() {
        return this.toShow;
    }

}
