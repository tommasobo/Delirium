package model;

import control.Position;

public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    private final Bounds bounds;
    private final int speed;
    private MovementPattern pattern;
    private PGActions action;

    public DinamicMovementManager(Position position, int speed, Bounds bounds) {
        super(position);
        this.speed = speed;
        this.bounds = bounds;
        this.pattern = this.getPosition().getDirection() == Position.Directions.RIGHT || this.getPosition().getDirection() == Position.Directions.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
        if (this.pattern == MovementPattern.LEFT_RIGHT) {
            this.action = PGActions.MRIGHT;
        } else {
            this.action = PGActions.JUMP;
        }
    }


    protected int getSpeed() {
        return speed;
    }
    
    protected Bounds getBounds() {
        return bounds;
    }
    
    public PGActions getAction() {
        return action;
    }


    public void setAction(PGActions action) {
        this.action = action;
    }
    
    public void setPattern(MovementPattern pattern) {
        this.pattern = pattern;
    }


    public MovementPattern getPattern() {
        return pattern;
    }
    
    protected Position linearMovement(Position actualPosition) {
        if (this.getPattern() == MovementPattern.LEFT_RIGHT) {
            if ((actualPosition.getPoint().getX() + this.getSpeed()) >= this.getBounds().getMaxX() && this.getAction() == PGActions.MRIGHT) {
                this.setAction(PGActions.MLEFT);
            } else if((actualPosition.getPoint().getX() - this.getSpeed()) <= this.getBounds().getMinX()) {
                this.setAction(PGActions.MRIGHT);
            }
            
        } else {
            if ((actualPosition.getPoint().getY() + this.getSpeed()) >= this.getBounds().getMaxY() && this.getAction() == PGActions.JUMP) {
                this.setAction(PGActions.DOWN);
            } else if((actualPosition.getPoint().getY() - this.getSpeed()) <= this.getBounds().getMinY()) {
                this.setAction(PGActions.JUMP);
            }
        }
        
        actualPosition.setPoint(this.getAction().getFunction().apply(actualPosition.getPoint(), this.getSpeed()));
        actualPosition.setDirection(this.getAction().getDirection().isPresent() ? this.getAction().getDirection().get() : actualPosition.getDirection());
       
        return actualPosition;
    }


    abstract public Position getNextMove();

}
