package model;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        super(position, bounds, action, speed, canFly);
        
    }

    @Override
    public Position getNextMove() {
    	return linearMovement(this.getPosition(), this.getBounds(), this.getSpeed());
    	
    }

}
