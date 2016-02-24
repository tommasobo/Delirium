package control;

import model.MovementTypes;
import model.ShootInfo;
import model.ShootTypes;

public class ShootInfoStore implements ShootInfo{
    private int minTime;
    private ShootTypes shootType;
    private int bulletDamage;
    private MovementTypes movementType;
    private int range;
    private int speed;
    
    
    public ShootInfoStore(final int minTime, final ShootTypes shootType, final int bulletDamage, 
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


    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }


    public void setShootType(ShootTypes shootType) {
        this.shootType = shootType;
    }


    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }


    public void setMovementType(MovementTypes movementType) {
        this.movementType = movementType;
    }


    public void setRange(int range) {
        this.range = range;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public ShootInfoStore getCopy() {
        return new ShootInfoStore(this.minTime, this.shootType, this.bulletDamage, this.movementType, this.range, this.speed);
    }
    
    
}
