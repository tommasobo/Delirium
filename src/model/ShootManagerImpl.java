package model;

import java.util.Optional;

import control.Dimension;
import control.Point;

public class ShootManagerImpl implements ShootManager {
    
    private static final int DEFAULT_OFFSET = 30;
    private static final Dimension DEFAULT_DIMENSION = new Dimension(20, 20);
    
    private final int minTime;
    private int count = 1;
    private final int bulletDamage;
    private final MovementTypes movementType;
    private final int range;
    private final int speed;
    
    
    public ShootManagerImpl(int minTime, int bulletDamage, MovementTypes movementType, int range, int speed) {
        this.minTime = minTime;
        this.bulletDamage = bulletDamage;
        this.movementType = movementType;
        this.range = range;
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
        position.setPoint(new Point(position.getDirection() == Directions.LEFT? position.getPoint().getX() - ShootManagerImpl.DEFAULT_DIMENSION.getWidth() - ShootManagerImpl.DEFAULT_OFFSET : position.getPoint().getX() + position.getDimension().getWidth() + ShootManagerImpl.DEFAULT_DIMENSION.getWidth() + ShootManagerImpl.DEFAULT_OFFSET, (int) position.getPoint().getY() + position.getDimension().getHeight()/2));
        position.setDimension(ShootManagerImpl.DEFAULT_DIMENSION);
        count++;
        return !this.isOnShoot(true) ? Optional.empty() : Optional.of(new EntitiesInfoImpl(code, position, Optional.of(new MovementInfo(this.speed, new Bounds(position.getPoint().getX() - this.range, position.getPoint().getX() + this.range, position.getPoint().getY() - this.range, position.getPoint().getY() + this.range), Actions.MOVE, true, this.movementType)), 1, LifePattern.WITH_LIFE, Optional.empty(), Optional.of(this.bulletDamage)));
    }


}
