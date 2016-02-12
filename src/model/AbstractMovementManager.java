package model;

import control.Point;

public abstract class AbstractMovementManager implements MovementManager {
    
    public static final int GRAVITY = 2;

    private final ModelPosition position;
    private boolean canFly; 
    private final Bounds bounds;

    public AbstractMovementManager(ModelPosition position, Bounds bounds, boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
    }


    abstract public ModelPosition getNextMove();
    
    // MAGNANI PART BEGIN
    protected ModelPosition applyGravity(ModelPosition position) {
        if(!canFly) {
            position.setPoint(new Point(position.getPoint().getX(), position.getPoint().getY() - AbstractMovementManager.GRAVITY));
            if(position.getPoint().getY() < bounds.getMinY()) {
                position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
            }
        }
        return position;
    }
    
    // MAGNANI PART FINISH
    
    /*private boolean checkBounds(ModelPosition position) {
        return bounds.getMinX() < position.getPoint().getX() && position.getPoint().getX() > bounds.getMaxX() && bounds.getMinY() < position.getPoint().getY() && position.getPoint().getY() > bounds.getMaxY();
    }
    
    private ModelPosition fixPositionBounds(ModelPosition position, boolean gravity) {
        if(gravity)
        {
            position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY()));
        } else {
            switch(this.position.getDirection()) {
                case RIGHT : position.setPoint(new Point(bounds.getMaxX(), position.getPoint().getY())); break;
                case LEFT : position.setPoint(new Point(bounds.getMinX(), position.getPoint().getY())); break;
                case UP : position.setPoint(new Point(position.getPoint().getX(), bounds.getMaxY())); break;
                case DOWN : position.setPoint(new Point(position.getPoint().getX(), bounds.getMinY())); break;
            }
        }
        
        return position;
    }*/
    
    protected Bounds getBounds() {
        return bounds;
    }
    
    public ModelPosition getPosition() {
        return position.getPosition();
    }
    
    public void setPosition(final Point point, final ModelDirections direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    protected void setDirection(final ModelDirections direction) {
        this.position.setDirection(direction);
    }
    
    protected ModelDirections getDirection() {
        return position.getDirection();
    }
    
    
}
