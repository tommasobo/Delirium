package view;

import javafx.animation.Timeline;

public enum Actions {
    
    JUMP("jump", Timeline.INDEFINITE),
    FALL("fall", Timeline.INDEFINITE),
    MOVE("move", Timeline.INDEFINITE),
    IDLE("idle", Timeline.INDEFINITE),
    SHOOT("shoot", 1),
    DEATH("death", 1);
    
    private final String string;
    private final int duration;

    Actions(final String string, final int duration) {
        this.string = string;
        this.duration = duration;
    }
    
    public String getString() {
        return this.string;
    }
    
    public int getDuration() {
        return this.duration;
    }
}
