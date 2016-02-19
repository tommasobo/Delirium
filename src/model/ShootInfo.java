package model;

public class ShootInfo {
    
    
    private final int minTime;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int offset;
    private final int speed;
    
    
    public ShootInfo(int minTime, int bulletDamage, MovementTypes movementType, int offset, int speed) {
        this.minTime = minTime;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.offset = offset;
        this.speed = speed;
    }


    public int getMinTime() {
        return minTime;
    }


    public int getBulletDamage() {
        return bulletDamage;
    }


    public MovementTypes getMovementType() {
        return movementType;
    }


    public int getOffset() {
        return offset;
    }
    
    public int getSpeed() {
        return speed;
    }

}
