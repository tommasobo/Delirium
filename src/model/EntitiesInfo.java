package model;

public interface EntitiesInfo {

    int getCode();

    int getLife();

    LifeManager getLifemanager();

    MovementTypes getMovementTypes();

    Position getPosition();

    Bounds getBounds();

    int getSpeed();

    boolean isCanFly();

    int getContactDamage();
    
    Actions getAction();

}