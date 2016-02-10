package model;


public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    private final Bounds bounds;
    private final int speed;
    private MovementPattern pattern;

    public DinamicMovementManager(ModelPosition position, int speed, Bounds bounds) {
        super(position);
        this.speed = speed;
        this.bounds = bounds;
        this.pattern = this.getPosition().getDirection() == ModelDirections.RIGHT || this.getPosition().getDirection() == ModelDirections.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
        
    }


    protected int getSpeed() {
        return speed;
    }
    
    protected Bounds getBounds() {
        return bounds;
    }

    
    public void setPattern(MovementPattern pattern) {
        this.pattern = pattern;
    }


    public MovementPattern getPattern() {
        return pattern;
    }
    
    protected ModelPosition linearMovement(ModelPosition actualPosition) {
        if (this.getPattern() == MovementPattern.LEFT_RIGHT) {
            if ((actualPosition.getPoint().getX() + this.getSpeed()) >= this.getBounds().getMaxX() && this.getPosition().getDirection() == ModelDirections.RIGHT) {
                this.getPosition().setDirection(ModelDirections.LEFT);
            } else if((actualPosition.getPoint().getX() - this.getSpeed()) <= this.getBounds().getMinX()) {
                this.getPosition().setDirection(ModelDirections.RIGHT);
            }
            
        } else {
            if ((actualPosition.getPoint().getY() + this.getSpeed()) >= this.getBounds().getMaxY() && this.getPosition().getDirection() == ModelDirections.UP) {
                this.getPosition().setDirection(ModelDirections.DOWN);
            } else if((actualPosition.getPoint().getY() - this.getSpeed()) <= this.getBounds().getMinY()) {
                this.getPosition().setDirection(ModelDirections.UP);
            }
        }
        
        actualPosition.setPoint(this.getPosition().getDirection().getFunction().apply(actualPosition.getPoint(), this.getSpeed()));
        actualPosition.setDirection(this.getPosition().getDirection());
       
        return actualPosition;
    }


    abstract public ModelPosition getNextMove();

}
