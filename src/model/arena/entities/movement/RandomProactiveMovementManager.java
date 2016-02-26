package model.arena.entities.movement;

import java.util.Random;

import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;
import model.arena.utility.MovementTypes;

class RandomProactiveMovementManager extends LinearProactiveMovementManager {

    private int count;

    public RandomProactiveMovementManager(final Position position, final Bounds bounds, final int speed,
            final boolean canFly, final MovementTypes movementTypes) {
        super(position, bounds, speed, canFly, movementTypes);
        this.count = 0;
    }

    @Override
    public Position getNextMove() {

        if (this.count % 10 == 0 || this.count == 0) {
            switch (new Random().nextInt(4)) {
            case 0:
                this.setAction(Actions.MOVE);
                this.setDirection(Directions.RIGHT);
                this.setMovementTypes(MovementTypes.HORIZONTAL_LINEAR);
                break;
            case 1:
                this.setAction(Actions.MOVE);
                this.setDirection(Directions.LEFT);
                this.setMovementTypes(MovementTypes.HORIZONTAL_LINEAR);
                break;
            case 2:
                this.setAction(Actions.JUMP);
                this.setMovementTypes(MovementTypes.VERTICAL_LINEAR);
                break;
            case 3:
                this.setAction(Actions.FALL);
                this.setMovementTypes(MovementTypes.VERTICAL_LINEAR);
                break;
            default:
                throw new IllegalStateException("Random number cannot be null");
            }

        }
        this.count++;

        return super.getNextMove();
    }

}
