package model;

public class LinearProactiveMovementManager extends DinamicMovementManager {
    
    private MovementTypes movementTypes;
    
    public LinearProactiveMovementManager(Position position, Bounds bounds, int speed, boolean canFly, MovementTypes movementTypes) {
        super(position, bounds, movementTypes == MovementTypes.HORIZONTAL_LINEAR ? Actions.MOVE : Actions.JUMP, speed, canFly);
        this.movementTypes = movementTypes;
    }

    @Override
    public Position getNextMove() {
        //Position position = this.getPosition();
        
        /*if(!UtilityMovement.checkBounds(position, this.getBounds(), this.getAction())) {
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
        }*/
        
        
        Position actualPosition = this.getPosition();
        if(!UtilityMovement.Move(actualPosition, this.getBounds(), this.getAction(), this.getSpeed()).isPresent()) {
            if(this.movementTypes == MovementTypes.HORIZONTAL_LINEAR) {
                if(actualPosition.getDirection() == Directions.LEFT) {
                    actualPosition.setDirection(Directions.RIGHT);
                } else {
                    actualPosition.setDirection(Directions.LEFT);
                }
            } else {
                if(this.getAction() == Actions.JUMP) {
                        this.setAction(Actions.FALL);
                } else {
                    this.setAction(Actions.JUMP);
                }
            }
            
        }
        
        return UtilityMovement.Move(actualPosition, this.getBounds(), this.getAction(), this.getSpeed()).orElseThrow(IllegalStateException::new);
    	
    }
    
    
    protected void setMovementTypes(MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }


    protected MovementTypes getMovementTypes() {
        return this.movementTypes;
    }


}
