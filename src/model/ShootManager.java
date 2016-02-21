package model;

import java.util.Optional;

import control.Dimension;

public interface ShootManager {
    
    public static final int BULLET_OFFSET = 10;
    public static final Dimension BULLET_DIMENSION = new Dimension(20, 20);
    
    boolean isOnShoot();
    
    Optional<EntitiesInfo> getBullet(final int code, final Position position);
    
    boolean haveShooted();

}
