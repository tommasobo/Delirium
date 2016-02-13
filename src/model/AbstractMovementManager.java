package model;

import control.Dimension;
import control.Point;

public abstract class AbstractMovementManager implements MovementManager {
    
    public static final int GRAVITY = 6;

    private final Position position;
    private boolean canFly; 
    private int speed;
    private final Bounds bounds;

    public AbstractMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.speed = speed;
    }


    abstract public Position getNextMove();
    
    // MAGNANI PART BEGIN
    protected Position applyGravity(Position position) {
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
    
    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }
    
    public void setPosition(final Point point, final Directions direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }
    
    protected void setDirection(final Directions direction) {
        this.position.setDirection(direction);
    }
    
    protected Directions getDirection() {
        return position.getDirection();
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeed( final int speed) {
        this.speed = speed;
    }
    
}
