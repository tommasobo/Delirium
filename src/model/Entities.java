package model;

import control.Dimension;
import control.Point;

public interface Entities {
    
    int getCode();
    
    int getLife();
    
    void setLife(final int life);
    
    LifeManager getLifeManager();
    
    /* INIZIO METODI PER POSIZIONE */
    Position getPosition();
    
    void setPosition(final Point point, final Directions direction);
    
    Point getPoint();
    
    void setPoint(final Point point);
    
    Dimension getDimension();

    Directions getDirection();

    void setDirection(final Directions direction);
    
    Position getNextMove();
    
    int getSpeed();
    
    void setSpeed(final int speed);
    
    Bounds getBounds();
    
    boolean isCanFly();
    
    /* FINE METODI PER POSIZIONE */
    
    int getContactDamage();
    
    void setContactDamage(final int contactDamage);
    
}
