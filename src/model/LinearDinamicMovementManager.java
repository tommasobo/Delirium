package model;

public class LinearDinamicMovementManager extends DinamicMovementManager {
    
    public LinearDinamicMovementManager(Position position, Bounds bounds, int speed, boolean canFly) {
        super(position, bounds, speed, canFly);
        
    }

    @Override
    public Position getNextMove() {
        //return this.linearMovement(this.getPosition());
    	return linearMovement(this.getPosition(), this.getBounds(), this.getSpeed());
    	
    }

}
