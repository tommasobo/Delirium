package model.arena.entities.shoot;

import java.util.Optional;

import model.arena.entities.Point;
import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;
import model.arena.utility.Actions;
import model.arena.utility.Bounds;
import model.arena.utility.Directions;
import model.arena.utility.MovementTypes;
import model.transfertentities.EntitiesInfo;
import model.transfertentities.EntitiesInfoImpl;
import model.transfertentities.MovementInfoImpl;

/**
 * This implementation shoot always shoot every minTime quantum of time.
 * @author josephgiovanelli
 *
 */
class MonsterShootManager implements ShootManager {

    private final int minTime;
    private int count = 1;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int range;
    private final int speed;

    MonsterShootManager(final int minTime, final int bulletDamage, final MovementTypes movementType,
            final int range, final int speed) {
        this.minTime = minTime;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.range = range;
        this.speed = speed;
    }

    @Override
    public boolean isOnShoot() {
        if (this.count >= minTime) {
            this.count = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean haveShooted() {
        return this.count == 0;
    }

    @Override
    public Optional<EntitiesInfo> getBullet(final int code, final Position position) {
        position.setPoint(new Point(position.getDirection() == Directions.LEFT
                ? position.getPoint().getX() - ShootManager.BULLET_DIMENSION.getWidth() - ShootManager.BULLET_OFFSET
                : position.getPoint().getX() + position.getDimension().getWidth()
                        + ShootManager.BULLET_DIMENSION.getWidth() + ShootManager.BULLET_OFFSET,
                (int) position.getPoint().getY() + position.getDimension().getHeight() / 2));
        position.setDimension(ShootManager.BULLET_DIMENSION);
        count++;
        return this.isOnShoot() ? Optional.of(new EntitiesInfoImpl(code, position,
                Optional.of(new MovementInfoImpl(this.speed,
                        new Bounds(position.getPoint().getX() - this.range, position.getPoint().getX() + this.range,
                                position.getPoint().getY() - this.range, position.getPoint().getY() + this.range),
                        Actions.MOVE, true, this.movementType)),
                1, LifePattern.WITH_LIFE, Optional.empty(), Optional.of(this.bulletDamage))) : Optional.empty();
    }

}
