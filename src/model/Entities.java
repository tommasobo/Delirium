package model;

import control.Dimension;
import control.Point;

public interface Entities {
    
    int getCode();
    
    int getLife();
    
    void setLife(final int life);
    
    LifeManager getLifeManager();
    
    /* INIZIO METODI PER POSIZIONE */
    ModelPosition getPosition();
    
    void setPosition(final Point point, final ModelDirections direction);
    
    Point getPoint();
    
    void setPoint(final Point point);
    
    Dimension getDimension();

    ModelDirections getDirection();

    void setDirection(final ModelDirections direction);
    
    ModelPosition getNextMove();
    
    int getSpeed();
    
    void setSpeed(final int speed);
    
    /* FINE METODI PER POSIZIONE */
    
    int getContactDamage();
    
    void setContactDamage(final int contactDamage);
    
}
