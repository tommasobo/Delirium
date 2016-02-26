package model.arena.entities;

import java.util.Optional;

import model.arena.entities.life.LifeManager;
import model.arena.entities.movement.MovementManager;
import model.arena.entities.shoot.ShootManager;
import model.arena.utility.Actions;
import model.arena.utility.Directions;

public class EntitiesImpl implements Entities {

    private final int code;
    private final Optional<Position> position;
    private final LifeManager lifeManager;
    private final Optional<MovementManager> movementManager;
    private final Optional<ShootManager> shootManager;
    private final Optional<Integer> contactDamage;

    public EntitiesImpl(final int code, final LifeManager lifeManager, final MovementManager movementManager,
            final Optional<ShootManager> shootManager, final Optional<Integer> contactDamage) {
        this.code = code;
        this.position = Optional.empty();
        this.lifeManager = lifeManager;
        this.movementManager = Optional.of(movementManager);
        this.contactDamage = contactDamage;
        this.shootManager = shootManager;
    }

    public EntitiesImpl(final int code, final LifeManager lifeManager, final Position position,
            final Optional<ShootManager> shootManager, final Optional<Integer> contactDamage) {
        this.code = code;
        this.position = Optional.of(position);
        this.lifeManager = lifeManager;
        this.movementManager = Optional.empty();
        this.contactDamage = contactDamage;
        this.shootManager = shootManager;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public Position getPosition() {
        return this.position.isPresent() ? new Position(this.position.get().getPoint(),
                this.position.get().getDirection(), this.position.get().getDimension())
                : this.movementManager.get().getPosition();
    }

    @Override
    public void setPosition(final Point point, final Directions direction) {
        if (this.position.isPresent()) {
            this.position.get().setPoint(point);
            this.position.get().setDirection(direction);
        } else {
            this.movementManager.get().setPosition(point, direction);
        }
    }

    @Override
    public LifeManager getLifeManager() {
        return this.lifeManager;
    }

    @Override
    public Optional<MovementManager> getMovementManager() {
        return this.movementManager;
    }

    @Override
    public Optional<ShootManager> getShootManager() {
        return this.shootManager;
    }

    @Override
    public Optional<Integer> getContactDamage() {
        return this.contactDamage;
    }

    @Override
    public Actions getAction() {

        if (this.shootManager.isPresent() && this.shootManager.get().haveShooted()) {
            return Actions.SHOOT;
        } else {
            if (this.movementManager.isPresent()) {
                return this.movementManager.get().getAction();
            } else {
                return Actions.STOP;
            }
        }
    }

    @Override
    public void setAction(final Actions action) {
        if (this.movementManager.isPresent()) {
            this.movementManager.get().setAction(action);
        }
    }

    @Override
    public void accept(final EntitiesVisitor visitor) {
        visitor.visit(this);
    }

}
