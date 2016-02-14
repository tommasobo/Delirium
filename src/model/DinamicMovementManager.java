package model;


public abstract class DinamicMovementManager extends AbstractMovementManager{
    
    
    private MovementPattern pattern;

    public DinamicMovementManager(Position position,Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
        this.pattern = this.getDirection() == Directions.RIGHT || this.getDirection() == Directions.LEFT ? MovementPattern.LEFT_RIGHT : MovementPattern.UP_DOWN;
    }




    
    public void setPattern(MovementPattern pattern) {
        this.pattern = pattern;
    }


    public MovementPattern getPattern() {
        return pattern;
    }
    
    protected Position linearMovement(Position position, Bounds bounds, int speed) {
        
    	Actions action = DinamicMovementManager.determinateAction(this.getDirection());
    	
    	position.setPoint(action.getFunction().apply(position.getPoint(), speed));
    	
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
    
    protected static Actions determinateAction(final Directions direction) {
        switch (direction) {
        case DOWN:
            return Actions.DOWN;
        case LEFT:
            return Actions.LEFT;
        case NONE:
            return Actions.STOP;
        case RIGHT:
            return Actions.RIGHT;
        case UP:
            return Actions.UP;
        default: 
            return Actions.STOP;
        }
    }


    abstract public Position getNextMove();

}
