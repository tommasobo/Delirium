package control;

import model.Actions;
import model.Bounds;
import model.MovementInfo;
import model.MovementTypes;

public class MovementInfoStore implements MovementInfo{

    private int speed;
    private Bounds bounds;
    private Actions actions;
    private boolean canFly;
    private MovementTypes movementTypes;
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Bounds getBounds() {
        return bounds;
    }
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
    public Actions getActions() {
        return actions;
    }
    public void setActions(Actions actions) {
        this.actions = actions;
    }
    public boolean isCanFly() {
        return canFly;
    }
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }
    public void setMovementTypes(MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }
    
    
}
