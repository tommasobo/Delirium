package model.transfertentities;

import model.arena.utility.MovementTypes;

/**
 * This class is the communication object that provides the services like own
 * interface.
 * 
 * @author josephgiovanelli
 *
 */
public class ShootInfoImpl implements ShootInfo {

    private final int minTime;
    private final ShootTypes shootType;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int range;
    private final int speed;

    /**
     * This constructor instances the fields with the opportune information.
     * 
     * @param minTime
     *            : how often fires the entity.
     * @param shootType
     *            : the type of the shoot. Hero or Monster.
     * @param bulletDamage
     *            : the damage of the bullet.
     * @param movementType
     *            : the types of movement of the bullet.
     * @param range
     *            : established limits offset of the bullet.
     * @param speed
     *            : the speed of the bullet.
     */
    public ShootInfoImpl(final int minTime, final ShootTypes shootType, final int bulletDamage,
            final MovementTypes movementType, final int range, final int speed) {
        this.minTime = minTime;
        this.shootType = shootType;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.range = range;
        this.speed = speed;
    }

    @Override
    public int getMinTime() {
        return this.minTime;
    }

    @Override
    public ShootTypes getShootType() {
        return this.shootType;
    }

    @Override
    public int getBulletDamage() {
        return this.bulletDamage;
    }

    @Override
    public MovementTypes getMovementType() {
        return this.movementType;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

}
