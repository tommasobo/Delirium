package model;

import java.util.Optional;

import control.Dimension;
import control.Point;

public class ShootManagerImpl implements ShootManager {
    
    private final int minTime;
    private int count = 0;
    
    
    
    public ShootManagerImpl(final int minTime) {
        this.minTime = minTime;
    }

    @Override
    public boolean isOnShoot() {
        if (this.count == minTime) {
            this.count = 0;
            return true;
        } else {
            return false;
        }
    }
   
    @Override
    public Optional<EntitiesInfo> getBullet(Position position) {
        position.setPoint(new Point(position.getDirection() == Directions.LEFT? position.getPoint().getX() : position.getPoint().getX() + position.getDimension().getWidth(), (int) position.getDimension().getHeight()/2));
        position.setDimension(new Dimension(10, 5));
        count++;
        return !this.isOnShoot() ? Optional.empty() : Optional.of(new EntitiesInfoImpl(0, 1, LifeManager.WITH_LIFE, MovementTypes.HORIZONTAL_LINEAR, position, new Bounds(position.getPoint().getX(), position.getPoint().getX() + 100, position.getPoint().getY(), position.getPoint().getY() + 100), Actions.MOVE, 10, true, 5));
    }


}
