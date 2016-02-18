package model;

import java.util.Optional;

import control.Point;

public interface Entities {
    
    int getCode();
    
    Position getPosition();
    
    void setPosition(final Point point, final Directions direction);
    
    Optional<LifeManager> getLifeManager();
    
    Optional<MovementManager> getMovementManager();
    
    Optional<ShootManager> getShootManager();
    
    Optional<Integer> getContactDamage();

    Actions getAction();
    
    void setAction(Actions action);
    
}
