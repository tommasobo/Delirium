package model;

import java.util.Optional;

public abstract class DinamicMovementManager implements MovementManager {

    public static final int GRAVITY = 6;

    private final Position position;
    private final boolean canFly;
    private int speed;
    private final Bounds bounds;
    private Actions action;

    public DinamicMovementManager(final Position position, final Bounds bounds, final Actions action, final int speed,
            final boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.speed = speed;
        this.action = action;
    }

    abstract public Position getNextMove();

    /**
     * @author Matteo Magnani
     * @param position
     * @return
     */
    protected Position applyGravity() {
        if (!canFly) {
            Optional<Position> opPos = UtilityMovement.Move(this.getPosition(), this.getBounds(), Actions.FALL,
                    DinamicMovementManager.GRAVITY);
            if (opPos.isPresent()) {
                if (this.getAction() == Actions.MOVE || this.getAction() == Actions.MOVEONJUMP) {
                    this.setAction(Actions.MOVEONFALL);
                } else {
                    this.setAction(Actions.FALL);
                }
                return opPos.get();
            } else {
                return this.getPosition();
            }
        }
        return this.getPosition();
    }

    public Position getPosition() {
        return new Position(this.position.getPoint(), this.position.getDirection(), this.position.getDimension());
    }

    public void setPosition(final Point point, final Directions direction) {
        this.position.setPoint(point);
        this.position.setDirection(direction);
    }

    protected void setDirection(final Directions direction) {
        this.position.setDirection(direction);
    }

    protected Directions getDirection() {
        return this.position.getDirection();
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public Bounds getBounds() {
        return this.bounds;
    }

    public boolean isCanFly() {
        return this.canFly;
    }

    public void setAction(final Actions action) {
        this.action = action;
    }

    public Actions getAction() {
        return this.action;
    }

}
