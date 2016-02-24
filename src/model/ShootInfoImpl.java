package model;

public class ShootInfoImpl implements ShootInfo {

    private final int minTime;
    private final ShootTypes shootType;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int range;
    private final int speed;

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
