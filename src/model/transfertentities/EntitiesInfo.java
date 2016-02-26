package model.transfertentities;

import java.util.Optional;

import model.arena.entities.Position;
import model.arena.entities.life.LifePattern;

public interface EntitiesInfo {

    int getCode();

    Optional<MovementInfo> getMovementInfo();

    Position getPosition();

    int getLife();

    LifePattern getLifePattern();

    Optional<ShootInfo> getShootInfo();

    Optional<Integer> getContactDamage();

}