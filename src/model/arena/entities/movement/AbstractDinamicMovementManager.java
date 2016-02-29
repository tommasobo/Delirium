package model.arena.entities.movement;

import java.util.Optional;

import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;
import model.arena.utility.UtilityMovement;

public abstract class AbstractDinamicMovementManager implements MovementManager {

    public static final int GRAVITY = 6;

    private final Position position;
    private final boolean canFly;
    private int speed;
    private final Bounds bounds;
    private Actions action;
    
    /**
     * This is the basic features that a dynamic entity have to have.
     * 
     * @param position
     *            : where is and the own properties.
     * @param bounds
     *            : the limits of his movement.
     * @param action
     *            : the first action.
     * @param speed
     *            : how much time do an action.
     * @param canFly
     *            : if he can fly.
     */
    AbstractDinamicMovementManager(final Position position, final Bounds bounds, final Actions action,
            final int speed, final boolean canFly) {
        this.position = position;
        this.canFly = canFly;
        this.bounds = bounds;
        this.speed = speed;
        this.action = action;
    }

    /**
     * This method is not implemented because is the base of any one.
     */
    public abstract Position getNextMove();

    /**
     * The method, if the entity cannot fly, apply the gravity and eventually modify the entity action
     * 
     * @author Matteo Magnani
     * @param position The actual position of the entity
     * @return The position after the gravity is applied
     */
    protected Position applyGravity(final Position pos) {
        if (!canFly) {
            final Optional<Position> opPos = UtilityMovement.move(pos, this.getBounds(), Actions.FALL,
                    AbstractDinamicMovementManager.GRAVITY);
            if (opPos.isPresent()) {
                if (this.getAction() == Actions.MOVE || this.getAction() == Actions.MOVEONJUMP
                        || this.getAction() == Actions.MOVEONFALL) {
                    this.setAction(Actions.MOVEONFALL);
                } else {
                    this.setAction(Actions.FALL);
                }
                return opPos.get();
            }
        }
        return pos;
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
