package model;

import control.Point;
import control.Position;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    private final Bounds bounds;
    private final MovementPattern pattern;
    private PGActions action;

    public LinearDinamicMovementManager(Position position, int speed, Bounds bounds) {
        super(position, speed);
        this.bounds = bounds;
        this.pattern = this.getPosition().getDirection() == Position.Directions.RIGHT || this.getPosition().getDirection() == Position.Directions.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
        if (this.pattern == MovementPattern.LEFT_RIGHT) {
            this.action = PGActions.MRIGHT;
        } else {
            this.action = PGActions.JUMP;
        }
    }

    @Override
    public Position getNextMove() {
        Point actualPoint = new Point(this.getPosition().getPoint().getX(), this.getPosition().getPoint().getX());
        Position actualPosition = new Position(actualPoint, this.getPosition().getDirection(), this.getPosition().getDimension());
        if (this.pattern == MovementPattern.LEFT_RIGHT) {
            if ((actualPoint.getX() + this.getSpeed()) >= bounds.getMaxX() && this.action == PGActions.MRIGHT) {
                this.action = PGActions.MLEFT;
            } else if((actualPoint.getX() - this.getSpeed()) <= bounds.getMinX()) {
                this.action = PGActions.MRIGHT;
            }
            
        } else {
            if ((actualPoint.getY() + this.getSpeed()) >= bounds.getMaxY() && this.action == PGActions.JUMP) {
                this.action = PGActions.DOWN;
            } else if((actualPoint.getY() - this.getSpeed()) <= bounds.getMinY()) {
                this.action = PGActions.JUMP;
            }
        }
        
        action.getFunction().apply(actualPoint, this.getSpeed());
        actualPosition.setDirection(action.getDirection().isPresent() ? action.getDirection().get() : actualPosition.getDirection());
        
        return actualPosition;
    }
    
    protected Bounds getBounds() {
        return bounds;
    }
    
    protected MovementPattern getPattern() {
        return pattern;
    }

}
