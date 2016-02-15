package model;


public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    
    private MovementPattern pattern;

    public DinamicMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        super(position, bounds, action, speed, canFly);
        this.pattern = this.getDirection() == Directions.RIGHT || this.getDirection() == Directions.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
    }




    
    public void setPattern(MovementPattern pattern) {
        this.pattern = pattern;
    }


    public MovementPattern getPattern() {
        return pattern;
    }
    
    protected Position linearMovement(Position position, Bounds bounds, int speed) {
        
    	
    	position.setPoint(this.getAction().getFunction().deterimnateNewPoint(position.getPoint(), speed, position.getDirection()));
    	
    	if(!checkBounds(position, bounds)) {
    		fixPositionBounds(position, bounds);
    		if(this.getPattern() == MovementPattern.LEFT_RIGHT) {
    			if(position.getDirection() == Directions.LEFT) {
    				position.setDirection(Directions.RIGHT);
    			} else {
    				position.setDirection(Directions.LEFT);
    			}
    		} else {
    			if(position.getDirection() == Directions.UP) {
    				position.setDirection(Directions.DOWN);
    			} else {
    				position.setDirection(Directions.UP);
    			}
    		}
    	}
    	
    	/*
        if (this.getPattern() == MovementPattern.LEFT_RIGHT) {
            if ((actualPosition.getPoint().getX() + this.getSpeed()) >= this.getBounds().getMaxX() && this.getDirection() == Directions.RIGHT) {
                this.setDirection(Directions.LEFT);
            } else if((actualPosition.getPoint().getX() - this.getSpeed()) <= this.getBounds().getMinX()) {
                this.setDirection(Directions.RIGHT);
            }
            
        } else {
            if ((actualPosition.getPoint().getY() + this.getSpeed()) >= this.getBounds().getMaxY() && this.getDirection() == Directions.UP) {
                this.setDirection(Directions.DOWN);
            } else if((actualPosition.getPoint().getY() - this.getSpeed()) <= this.getBounds().getMinY()) {
                this.setDirection(Directions.UP);
            }
        }
        
        Actions action = DinamicMovementManager.determinateAction(this.getDirection());
        
        actualPosition.setPoint(action.getFunction().apply(actualPosition.getPoint(), this.getSpeed()));
        actualPosition.setDirection(this.getDirection());
        */
       
        
        
        return position;
    }
    


    abstract public Position getNextMove();

}
