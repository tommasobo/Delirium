package model;

import java.util.Optional;

public interface EntitiesInfo {

    int getCode();

    Optional<MovementInfo> getMovementInfo();

    Position getPosition();

    int getLife();

    LifePattern getLifePattern();

    Optional<ShootInfo> getShootInfo();

    Optional<Integer> getContactDamage();

}