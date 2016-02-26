package model.arena.entities.movement;

import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;

class ReactiveMovementManager extends AbstractDinamicMovementManager {

    public ReactiveMovementManager(final Position position, final Bounds bounds, final Actions action, final int speed,
            final boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        return super.applyGravity(this.getPosition());
    }

}
