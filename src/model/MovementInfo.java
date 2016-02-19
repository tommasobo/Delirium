package model;

public class MovementInfo {
    
    private final int speed;
    private final Bounds bounds;
    private final Actions actions;
    private final boolean canFly;
    private final MovementTypes movementTypes;
    
    public MovementInfo(int speed, Bounds bounds, Actions actions, boolean canFly, MovementTypes movementTypes) {
        this.speed = speed;
        this.bounds = bounds;
        this.actions = actions;
        this.canFly = canFly;
        this.movementTypes = movementTypes;
    }

    public int getSpeed() {
        return speed;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public Actions getActions() {
        return actions;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public MovementTypes getMovementTypes() {
        return movementTypes;
    }
    
    
    

}
