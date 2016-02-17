package model;

import java.util.Optional;

public interface ShootManager {
    
    boolean isOnShoot();
    
    Optional<EntitiesInfo> getBullet(Position position);
    
    boolean haveShooted();

}
