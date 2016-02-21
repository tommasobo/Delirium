package model;

import java.util.Optional;

public interface EntitiesInfo {

    int getCode();

    Optional<MovementInfoImpl> getMovementInfo();

    void setMovementInfo(Optional<MovementInfoImpl> movementInfo);

    Position getPosition();

    int getLife();

    LifePattern getLifePattern();

    Optional<ShootInfoImpl> getShootInfo();

    Optional<Integer> getContactDamage();

}