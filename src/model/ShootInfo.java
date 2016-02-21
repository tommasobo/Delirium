package model;

public class ShootInfo {
    
    
    private final int minTime;
    private final ShootTypes shootType;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int range;
    private final int speed;
    
    
    public ShootInfo(int minTime, ShootTypes shootType, int bulletDamage, MovementTypes movementType, int range, int speed) {
        this.minTime = minTime;
        this.shootType = shootType;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.range = range;
        this.speed = speed;
    }


    public int getMinTime() {
        return minTime;
    }
    
    public ShootTypes getShootType() {
        return shootType;
    }


    public int getBulletDamage() {
        return bulletDamage;
    }


    public MovementTypes getMovementType() {
        return movementType;
    }


    public int getRange() {
        return range;
    }
    
    public int getSpeed() {
        return speed;
    }

}
