package model;

public class LinearProactiveMovementManager extends DinamicMovementManager {

    private MovementTypes movementTypes;

    public LinearProactiveMovementManager(final Position position, final Bounds bounds, final int speed,
            final boolean canFly, final MovementTypes movementTypes) {
        super(position, bounds, movementTypes == MovementTypes.HORIZONTAL_LINEAR ? Actions.MOVE : Actions.JUMP, speed,
                canFly);
        this.movementTypes = movementTypes;
    }

    @Override
    public Position getNextMove() {
        Position actualPosition = this.getPosition();
        this.setAction(this.getAction() == Actions.MOVEONFALL ? Actions.MOVE : this.getAction());

        if (!UtilityMovement.Move(actualPosition, this.getBounds(), this.getAction(), this.getSpeed()).isPresent()) {
            if (this.movementTypes == MovementTypes.HORIZONTAL_LINEAR) {
                actualPosition.setDirection(
                        actualPosition.getDirection() == Directions.LEFT ? Directions.RIGHT : Directions.LEFT);

            } else {
                this.setAction(UtilityMovement.splitActions(this.getAction()).contains(Actions.JUMP) ? Actions.FALL
                        : Actions.JUMP);
            }
        }

        actualPosition = UtilityMovement.Move(actualPosition, this.getBounds(), this.getAction(), this.getSpeed())
                .orElseThrow(IllegalStateException::new);

        return this.movementTypes == MovementTypes.HORIZONTAL_LINEAR ? super.applyGravity(actualPosition)
                : actualPosition;

    }

    protected void setMovementTypes(final MovementTypes movementTypes) {
        this.movementTypes = movementTypes;
    }

    protected MovementTypes getMovementTypes() {
        return this.movementTypes;
    }

}
