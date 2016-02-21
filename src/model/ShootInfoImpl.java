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


    /* (non-Javadoc)
     * @see model.ShootInfo#getMinTime()
     */
    @Override
    public int getMinTime() {
        return minTime;
    }
    
    /* (non-Javadoc)
     * @see model.ShootInfo#getShootType()
     */
    @Override
    public ShootTypes getShootType() {
        return shootType;
    }


    /* (non-Javadoc)
     * @see model.ShootInfo#getBulletDamage()
     */
    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }


    /* (non-Javadoc)
     * @see model.ShootInfo#getMovementType()
     */
    @Override
    public MovementTypes getMovementType() {
        return movementType;
    }


    /* (non-Javadoc)
     * @see model.ShootInfo#getRange()
     */
    @Override
    public int getRange() {
        return range;
    }
    
    /* (non-Javadoc)
     * @see model.ShootInfo#getSpeed()
     */
    @Override
    public int getSpeed() {
        return speed;
    }

}
