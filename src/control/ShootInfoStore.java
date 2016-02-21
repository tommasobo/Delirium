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
    
    public int getMinTime() {
        return minTime;
    }
    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }
    public ShootTypes getShootType() {
        return shootType;
    }
    public void setShootType(ShootTypes shootType) {
        this.shootType = shootType;
    }
    public int getBulletDamage() {
        return bulletDamage;
    }
    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }
    public MovementTypes getMovementType() {
        return movementType;
    }
    public void setMovementType(MovementTypes movementType) {
        this.movementType = movementType;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
}
