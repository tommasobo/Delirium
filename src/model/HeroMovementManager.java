package model;

import java.util.Optional;

public class HeroMovementManager extends AbstractMovementManager{
    
    private boolean onJump = false;
    private int time = 0;

    public HeroMovementManager(Position position, Bounds bounds, Actions action, int speed, boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    /**
     * 	@author Matteo Magnani
     */
    @Override
    public Position getNextMove() {
    	Position newPosition = this.getPosition();
        
        //TODO eventualmente aumetare il salto ed applicare comunque la gravità, a livello astratto è forse più logico
        if(!onJump) {
            if(this.getAction() == Actions.MOVEONJUMP) {
                this.setAction(Actions.MOVE);
            }
            newPosition = applyGravity();
        }
        
        if(this.getAction() != Actions.FALL && this.getAction() != Actions.MOVEONFALL && (this.getAction() == Actions.JUMP || this.getAction() == Actions.MOVEONJUMP) && !onJump) {
        	time = 0;
            this.onJump = true;
        }
        
        if (onJump) {
            if(time < 60) {
                if(this.getAction() == Actions.MOVE) {
                    this.setAction(Actions.MOVEONJUMP);
                } else if(this.getAction() != Actions.MOVEONJUMP){
                    this.setAction(Actions.JUMP);
                }
                
                
                System.out.println(this.getAction());
                
                
                
                time++;
            } else {
                onJump = false;
            }
        }
        
        System.out.println(this.getAction());
        
        for(Actions e : UtilityMovement.splitActions(this.getAction())) {
            if(e != Actions.FALL) {
                Optional<Position> op = UtilityMovement.Move(newPosition, this.getBounds(), e, this.getSpeed());
                if(op.isPresent()) {
                    newPosition = op.get();
                }
            }
        }
        
        return newPosition;
    }

    

}
