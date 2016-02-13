package model;

import control.Point;

public interface MovementManager {
    
    Position getPosition();
    
    void setPosition(Point point, Directions direction);
    
    int getSpeed();
    
    void setSpeed( final int speed);
    
    Position getNextMove();
}
