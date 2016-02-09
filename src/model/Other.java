package model;

import java.util.Optional;

public interface Other {

    int getLife();

    void setLife(int life);

    int getCode();

    LifeManager getLifeManager();

    MovementManager getMovementManager();

    Optional<Integer> getContactDamage();

}