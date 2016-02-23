package model;

import java.util.Optional;

public class HeroMovementManager extends AbstractMovementManager{
    
    private boolean onJump = false;
    private int time = 0;
    private boolean onPlatform = false;

    public HeroMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    /**
     * 	@author Matteo Magnani
     */
    @Override
    public Position getNextMove() {
    	Position newPosition = this.getPosition();
        
    	if(UtilityMovement.splitActions(this.getAction()).stream().anyMatch(e -> e == Actions.JUMP) && !onJump && onPlatform) {
    	    time = 0;
            this.onJump = true;
    	}
    	
    	
    	//TODO eventualmente aumetare il salto ed applicare comunque la gravità, a livello astratto è forse più logico
        if(!onJump /*&& !onPlatform*/) {
            newPosition = applyGravity();
        }
        
        this.onPlatform = false;
        
        /*if(!UtilityMovement.splitActions(this.getAction()).stream().anyMatch(e -> e == Actions.FALL) && UtilityMovement.splitActions(this.getAction()).stream().anyMatch(e -> e == Actions.JUMP) && !onJump) {
            time = 0;
            this.onJump = true;
        }*/
        
        if (onJump) {
            if(time < 15) {
                if(UtilityMovement.splitActions(this.getAction()).contains(Actions.FALL)) {
                    time = 15;
                }
                if(this.getAction() == Actions.MOVE) {
                    this.setAction(Actions.MOVEONJUMP);
                } else if(this.getAction() != Actions.MOVEONJUMP){
                    this.setAction(Actions.JUMP);
                }
                time++;
            } else {
                onJump = false;
            }
        }
        
        for(Actions e : UtilityMovement.splitActions(this.getAction())) {
            if(e != Actions.FALL) {
                Optional<Position> op = UtilityMovement.Move(newPosition, this.getBounds(), e, this.getSpeed());
                if(op.isPresent()) {
                    newPosition = op.get();
                } else if(e == Actions.JUMP) {
                    time = 15;
                }
            }
        }
        
        return newPosition;
    }
    
    public void setOnPlatform(boolean bool) {
        this.onPlatform = bool;
    }

    

}
