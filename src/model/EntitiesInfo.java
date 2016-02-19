package model;

import java.util.Optional;

public interface EntitiesInfo {

    int getCode();

    Optional<MovementInfo> getMovementInfo();

    void setMovementInfo(Optional<MovementInfo> movementInfo);

    Position getPosition();

    int getLife();

    LifePattern getLifePattern();

    Optional<ShootInfo> getShootInfo();

    Optional<Integer> getContactDamage();

}