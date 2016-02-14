package model;

public class HeroMovementManager extends DinamicMovementManager{
    
    private boolean onJump = false;
    private int time = 0;

    public HeroMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
    }

    /**
     * 	@author Matteo Magnani
     */
    @Override
    public Position getNextMove() {
    	Position newPosition = this.getPosition();
    	Actions action = determinateAction(newPosition.getDirection());
    	
        //TODO eventualmente aumetare il salto ed applicare comunque la gravità, a livello astratto è forse più logico
        if(!onJump) {
            newPosition = applyGravity(newPosition);
        }
        
        if(newPosition.getPoint().getY() == this.getPosition().getPoint().getY()) {
        	newPosition.setDirection(Directions.NONE);
        	if(this.getDirection() == Directions.UP && !onJump) {
        		time = 0;
            	this.onJump = true;
        	}
        }
        
        if (onJump) {
            if(time < 200) {
                newPosition.setPoint(Actions.UP.getFunction().apply(newPosition.getPoint(), 5));
                newPosition.setDirection(Directions.UP);
                //TODO metti controllo bounds salto
                if (!checkBounds(newPosition, this.getBounds())) {
                    fixPositionBounds(newPosition, getBounds());
                    time = 200;
                }
                time++;
            } else {
                onJump = false;
            }
        }
        
        //applico le funzioni dopo il calcolo dell'eventuale salto o caduta in modo da poter settare la reale direzione del personaggio 
        if (action == Actions.LEFT || action == Actions.RIGHT) {
        	newPosition.setPoint(action.getFunction().apply(newPosition.getPoint(), this.getSpeed()));
        	newPosition.setDirection(this.getDirection());
        }
        
        if(!checkBounds(newPosition, getBounds())) {
    		fixPositionBounds(newPosition, getBounds());
    	}
        
        return newPosition;
    }

    

}
