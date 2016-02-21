package model;

public interface ShootInfo {

    int getMinTime();

    ShootTypes getShootType();

    int getBulletDamage();

    MovementTypes getMovementType();

    int getRange();

    int getSpeed();

}