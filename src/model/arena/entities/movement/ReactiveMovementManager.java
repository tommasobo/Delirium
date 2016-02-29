package model.arena.entities.movement;

import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;

/**
 * This construct add anything else but the fields are important because
 * some reactive element in a future can changed because others move them.
 * In the implementation of the getNextMove is applied only the gravity.
 * This is done in order to make extensible the code.
 */
class ReactiveMovementManager extends AbstractDinamicMovementManager {

    
    ReactiveMovementManager(final Position position, final Bounds bounds, final Actions action, final int speed,
            final boolean canFly) {
        super(position, bounds, action, speed, canFly);
    }

    @Override
    public Position getNextMove() {
        return super.applyGravity(this.getPosition());
    }

}
