package model;

import java.util.Optional;

public interface EntitiesInfoToControl {

    int getCode();

    int getLife();

    Position getPosition();

    Actions getAction();

    Optional<Integer> getSpeed();

}
