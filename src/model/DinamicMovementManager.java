package model;


public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    
    private MovementPattern pattern;

    public DinamicMovementManager(ModelPosition position,Bounds bounds, boolean canFly) {
        super(position, bounds, canFly);
        this.pattern = this.getPosition().getDirection() == ModelDirections.RIGHT || this.getPosition().getDirection() == ModelDirections.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
    }




    
    public void setPattern(MovementPattern pattern) {
        this.pattern = pattern;
    }


    public MovementPattern getPattern() {
        return pattern;
    }
    
    protected ModelPosition linearMovement(ModelPosition actualPosition) {
        if (this.getPattern() == MovementPattern.LEFT_RIGHT) {
            if ((actualPosition.getPoint().getX() + this.getPosition().getSpeed()) >= this.getBounds().getMaxX() && this.getDirection() == ModelDirections.RIGHT) {
                this.setDirection(ModelDirections.LEFT);
            } else if((actualPosition.getPoint().getX() - this.getPosition().getSpeed()) <= this.getBounds().getMinX()) {
                this.setDirection(ModelDirections.RIGHT);
            }
            
        } else {
            if ((actualPosition.getPoint().getY() + this.getPosition().getSpeed()) >= this.getBounds().getMaxY() && this.getDirection() == ModelDirections.UP) {
                this.setDirection(ModelDirections.DOWN);
            } else if((actualPosition.getPoint().getY() - this.getPosition().getSpeed()) <= this.getBounds().getMinY()) {
                this.setDirection(ModelDirections.UP);
            }
        }
        
        actualPosition.setPoint(this.getDirection().getFunction().apply(actualPosition.getPoint(), this.getPosition().getSpeed()));
        actualPosition.setDirection(this.getDirection());
       
        //TODO eventualmente spostare da qua l'apply gravity, al momento sembra il sistema per richiamarlo meno volte
        actualPosition = applyGravity(actualPosition);
        
        return actualPosition;
    }


    abstract public ModelPosition getNextMove();

}
