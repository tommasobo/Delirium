package model;

import java.util.Optional;

public interface ShootManager {
    
    boolean isOnShoot(boolean wannaShoot);
    
    Optional<EntitiesInfo> getBullet(int code, Position position);
    
    boolean haveShooted();

}
