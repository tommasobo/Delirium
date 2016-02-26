package control;

import model.arena.utility.MovementTypes;
import model.transfertentities.ShootInfo;
import model.transfertentities.ShootTypes;

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


    public void setMinTime(final int minTime) {
        this.minTime = minTime;
    }


    public void setShootType(final ShootTypes shootType) {
        this.shootType = shootType;
    }


    public void setBulletDamage(final int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }


    public void setMovementType(final MovementTypes movementType) {
        this.movementType = movementType;
    }


    public void setRange(final int range) {
        this.range = range;
    }


    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    
}
