package model;

public class LinearDinamicMovementManager extends AbstractMovementManager {
    
    private MovementTypes movementTypes;
    
    public LinearDinamicMovementManager(Position position, Bounds bounds, int speed, boolean canFly, MovementTypes movementTypes) {
        super(position, bounds, movementTypes == MovementTypes.HORIZONTAL_LINEAR ? Actions.MOVE : Actions.JUMP, speed, canFly);
        this.movementTypes = movementTypes;
    }

    @Override
    public Position getNextMove() {
        Position position = this.getPosition();
        position.setPoint(this.getAction().getFunction().deterimnateNewPoint(position.getPoint(), this.getSpeed(), position.getDirection()));
        
        if(!UtilityMovement.checkBounds(position, this.getBounds(), this.getAction())) {
                UtilityMovement.fixPositionBounds(position, this.getBounds(), this.getAction());
                if(this.movementTypes == MovementTypes.HORIZONTAL_LINEAR) {
                        if(position.getDirection() == Directions.LEFT) {
                                position.setDirection(Directions.RIGHT);
                        } else {
                                position.setDirection(Directions.LEFT);
                        }
                } else {
                        if(this.getAction() == Actions.JUMP) {
                                this.setAction(Actions.FALL);
                        } else {
                            this.setAction(Actions.JUMP);
                        }
                }
        }
        
        return position;
    	
    }
    
    
    protected void setMovementTypes(MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }


    protected MovementTypes getMovementTypes() {
        return this.movementTypes;
    }


}
