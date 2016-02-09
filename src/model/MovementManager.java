package model;

import control.Point;
import control.Position;

public interface MovementManager {
    
    Position getPosition();
    
    void setPosition(Point point, Position.Directions direction);
    
    Position getNextMove();
}
