package view;

public enum Actions {
    
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    IDLE("idle");
    //SHOOT("shoot"),
    //DEATH("death");
    
    private final String string;

    private Actions(final String string) {
        this.string = string;
    }
    
    public String getString() {
        return this.string;
    }

}
