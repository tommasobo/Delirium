package model;

import java.util.Optional;

import control.Dimension;
import control.Point;

public class ShootManagerImpl implements ShootManager {
    
    private final int minTime;
    private int count = 1;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int offset;
    private final int speed;
    
    
    public ShootManagerImpl(int minTime, int bulletDamage, MovementTypes movementType, int offset, int speed) {
        this.minTime = minTime;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.offset = offset;
        this.speed = speed;
    }

    @Override
    public boolean isOnShoot(boolean wannaShoot) {
        if (this.count >= minTime) {
            this.count = wannaShoot? 0 : this.count;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean haveShooted() {
        if (this.count == 0) {
            return true;
        } else {
            return false;
        }
    }
   
    @Override
    public Optional<EntitiesInfo> getBullet(int code, Position position) {
        position.setPoint(new Point(position.getDirection() == Directions.LEFT? position.getPoint().getX() : position.getPoint().getX() + position.getDimension().getWidth(), (int) position.getPoint().getY() + position.getDimension().getHeight()/2));
        position.setDimension(new Dimension(30, 30));
        count++;
        return !this.isOnShoot(true) ? Optional.empty() : Optional.of(new EntitiesInfoImpl(code, position, Optional.of(new MovementInfo(this.speed, new Bounds(position.getPoint().getX() - this.offset, position.getPoint().getX() + this.offset, position.getPoint().getY() - this.offset, position.getPoint().getY() + this.offset), Actions.MOVE, true, this.movementType)), 1, LifePattern.WITH_LIFE, Optional.empty(), Optional.of(this.bulletDamage)));
    }


}
