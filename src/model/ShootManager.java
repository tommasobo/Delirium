package model;

import java.util.Optional;

import utility.Dimension;

public interface ShootManager {

    int BULLET_OFFSET = 10;
    Dimension BULLET_DIMENSION = new Dimension(20, 20);

    boolean isOnShoot();

    Optional<EntitiesInfo> getBullet(final int code, final Position position);

    boolean haveShooted();

}
