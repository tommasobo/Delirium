package model;

public class EntitiesInfoToControlImpl implements EntitiesInfoToControl {
    
    private final int code;
    private final int life;
    private final Position position;
    private final Actions action;
    public EntitiesInfoToControlImpl(final int code, final int life, final Position position, final Actions action) {
        this.code = code;
        this.life = life;
        this.position = position;
        this.action = action;
    }
    
    public int getCode() {
        return code;
    }
    public int getLife() {
        return life;
    }
    public Position getPosition() {
        return position;
    }
    public Actions getAction() {
        return action;
    }
    
    

    

}
