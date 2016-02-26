package control;

import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.MovementTypes;
import model.transfertentities.MovementInfo;

public class MovementInfoStore implements MovementInfo{
    private int speed;
    private Bounds bounds;
    private Actions actions;
    private boolean canFly;
    private MovementTypes movementTypes;
    
    public MovementInfoStore(final int speed, final Bounds bounds, final Actions actions, final boolean canFly, final MovementTypes movementTypes) {
        this.speed = speed;
        this.bounds = bounds;
        this.actions = actions;
        this.canFly = canFly;
        this.movementTypes = movementTypes;
    }

    public MovementInfoStore(final MovementInfoStore m) {
        this.speed = m.getSpeed();
        this.bounds = m.getBounds();
        this.actions = m.getActions();
        this.canFly = m.isCanFly();
        this.movementTypes = m.getMovementTypes();
    }
    
    @Override
    public int getSpeed() {
        return speed;
    }

    
    @Override
    public Bounds getBounds() {
        return new Bounds(this.bounds.getMinX(), this.bounds.getMaxX(), this.bounds.getMinY(), this.bounds.getMaxY());
    }

    
    @Override
    public Actions getActions() {
        return actions;
    }

    
    @Override
    public boolean isCanFly() {
        return canFly;
    }

    
    @Override
    public MovementTypes getMovementTypes() {
        return movementTypes;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public void setBounds(final Bounds bounds) {
        this.bounds = bounds;
    }

    public void setActions(final Actions actions) {
        this.actions = actions;
    }

    public void setCanFly(final boolean canFly) {
        this.canFly = canFly;
    }

    public void setMovementTypes(final MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }
    
    
}
