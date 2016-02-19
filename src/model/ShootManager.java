package model;

import java.util.Optional;

public interface ShootManager {
    
    boolean isOnShoot();
    
    Optional<EntitiesInfo> getBullet(int code, Position position);
    
    boolean haveShooted();

}
