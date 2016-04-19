package model.arena.entities.movement;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.UtilityMovement;
import utility.Pair;

class HeroMovementManagerImpl extends AbstractDinamicMovementManager implements HeroMovementManager {

    private static final int JUMPTIME = 15;
    private boolean onJump;
    private int time;
    private boolean onPlatform;
    private boolean bended;

    public HeroMovementManagerImpl(final Position position, final Bounds bounds, final Actions action, final int speed,
            final boolean canFly) {
        super(position, bounds, action, speed, canFly);
        this.onJump = false;
        this.time = 0;
        this.onPlatform = false;
        this.bended = false;
    }

    /**
     * @author Matteo Magnani
     */
    
    private void switchDimension() {
        super.setDimension(new Pair<>(this.getPosition().getDimension().getY(), this.getPosition().getDimension().getX()));
    }
    
    @Override
    public Position getNextMove() {
        Position newPosition = this.getPosition();

        // If hero is on a platform, isn't already on jump ad is on platform
        // (isn't falling) can start to jump
        if (UtilityMovement.splitActions(this.getAction()).stream().anyMatch(e -> e == Actions.JUMP) && !onJump
                && onPlatform) {
            time = 0;
            this.onJump = true;
        }

        if (!onJump) {
            newPosition = applyGravity(newPosition);
        }

        this.onPlatform = false;

        if (onJump) {
            if (time < JUMPTIME) {

                if (this.getAction() == Actions.MOVE) {
                    this.setAction(Actions.MOVEONJUMP);
                } else if (this.getAction() != Actions.MOVEONJUMP) {
                    this.setAction(Actions.JUMP);
                }
                time++;
            } else {
                onJump = false;
            }
        }

        for (final Actions e : UtilityMovement.splitActions(this.getAction())) {
            // applico ogni azione diversa dal fall(gia applicata
            // dall'applyGravity)
            if (e != Actions.FALL) {
                final Optional<Position> op = UtilityMovement.move(newPosition, this.getBounds(), e, this.getSpeed());
                if (op.isPresent()) {
                    newPosition = op.get();
                } else if (e == Actions.JUMP) {
                    // Se l'hero ha raggiunto i bounds e sta saltando faccio
                    // terminare il salto
                    time = JUMPTIME;
                }
            }
        }

        return newPosition;
    }

    @Override
    public void setOnPlatform(final boolean bool) {
        this.onPlatform = bool;
    }

    @Override
    public void bendPG(boolean bool) {
        if(bool != this.bended) {
            this.switchDimension();
        }
        this.bended = bool;
    }

    @Override
    public boolean isBended() {
        return this.bended;
    }

}
