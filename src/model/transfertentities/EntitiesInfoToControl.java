package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.utility.Actions;

public interface EntitiesInfoToControl {

    int getCode();

    int getLife();

    Position getPosition();

    Actions getAction();

    Optional<Integer> getSpeed();

}
