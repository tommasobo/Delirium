package control;

import model.arena.utility.MovementTypes;
import model.transfertentities.ShootInfo;
import model.transfertentities.ShootTypes;

/**
 * Class that contains entities shoot traits
 * @author Matteo Magnani
 *
 */
public class ShootInfoStore implements ShootInfo{
    private int minTime;
    private ShootTypes shootType;
    private int bulletDamage;
    private MovementTypes movementType;
    private int range;
    private int speed;
    
    /**
     * 
     * @param minTime Entity's min time between two shoots
     * @param shootType Entity's shoot type
     * @param bulletDamage Entity's bullets damage
     * @param movementType Movement type of Entity's bullets
     * @param range Entity's bullets range
     * @param speed Entity's bullets speed
     */
    public ShootInfoStore(final int minTime, final ShootTypes shootType, final int bulletDamage, 
            final MovementTypes movementType, final int range, final int speed) {
        this.minTime = minTime;
        this.shootType = shootType;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.range = range;
        this.speed = speed;
    }

    public ShootInfoStore(final ShootInfoStore s) {
        this.minTime = s.getMinTime();
        this.shootType = s.getShootType();
        this.bulletDamage = s.getBulletDamage();
        this.movementType = s.getMovementType();
        this.range = s.getRange();
        this.speed = s.getSpeed();
    }
    
    @Override
    public int getMinTime() {
        return minTime;
    }
    
    
    @Override
    public ShootTypes getShootType() {
        return shootType;
    }


    
    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }


    
    @Override
    public MovementTypes getMovementType() {
        return movementType;
    }


    
    @Override
    public int getRange() {
        return range;
    }
    
    
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * 
     * @param minTime Entity's min time between two shoots
     */
    public void setMinTime(final int minTime) {
        this.minTime = minTime;
    }

    /**
     * 
     * @param shootType Entity's shoot type
     */
    public void setShootType(final ShootTypes shootType) {
        this.shootType = shootType;
    }

    /**
     * 
     * @param bulletDamage Entity's bullets damage
     */
    public void setBulletDamage(final int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    /**
     * 
     * @param movementType Movement type of Entity's bullets
     */
    public void setMovementType(final MovementTypes movementType) {
        this.movementType = movementType;
    }

    /**
     * 
     * @param range Entity's bullets range
     */
    public void setRange(final int range) {
        this.range = range;
    }

    /**
     * 
     * @param speed Entity's bullets speed
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    
}
