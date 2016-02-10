package model;

import control.Point;

public interface MovementManager {
    
    ModelPosition getPosition();
    
    void setPosition(Point point, ModelDirections direction);
    
    ModelPosition getNextMove();
}
