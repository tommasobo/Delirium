package model.arena.entities.movement;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.UtilityMovement;

class HeroMovementManagerImpl extends AbstractDinamicMovementManager implements HeroMovementManager {
    
    private static final int JUMPTIME = 15;
    private boolean onJump;
    private int time;
    private boolean onPlatform;

    public HeroMovementManagerImpl(final Position position, final Bounds bounds, final Actions action, final int speed, final boolean canFly) {
        super(position, bounds, action, speed, canFly);
        this.onJump = false;
        this.time = 0;
        this.onPlatform = false;
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
    	
        if(!onJump) {
            newPosition = applyGravity(newPosition);
        }
        
        this.onPlatform = false;
        
        if (onJump) {
            if(time < JUMPTIME) {
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
        
        for(final Actions e : UtilityMovement.splitActions(this.getAction())) {
            if(e != Actions.FALL) {
                final Optional<Position> op = UtilityMovement.move(newPosition, this.getBounds(), e, this.getSpeed());
                if(op.isPresent()) {
                    newPosition = op.get();
                } else if(e == Actions.JUMP) {
                    time = JUMPTIME;
                }
            }
        }
        
        return newPosition;
    }
    
    /* (non-Javadoc)
     * @see model.arena.entities.movement.HeroMovementManager#setOnPlatform(boolean)
     */
    @Override
    public void setOnPlatform(final boolean bool) {
        this.onPlatform = bool;
    }

    

}
